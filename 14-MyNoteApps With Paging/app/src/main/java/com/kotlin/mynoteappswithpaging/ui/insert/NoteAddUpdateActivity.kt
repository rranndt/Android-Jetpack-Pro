package com.kotlin.mynoteappswithpaging.ui.insert

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kotlin.mynoteappswithpaging.R
import com.kotlin.mynoteappswithpaging.database.Note
import com.kotlin.mynoteappswithpaging.databinding.ActivityNoteAddUpdateBinding
import com.kotlin.mynoteappswithpaging.helper.DateHelper
import com.kotlin.mynoteappswithpaging.helper.ViewModelFactory

class NoteAddUpdateActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_NOTE = "extra_note"
        const val EXTRA_POSITION = "extra_position"
        const val REQUEST_ADD = 100
        const val RESULT_ADD = 101
        const val REQUEST_UPDATE = 200
        const val RESULT_UPDATE = 201
        const val RESULT_DELETE = 301
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }

    private var isEdit = false
    private var note: Note? = null
    private var position = 0

    private lateinit var noteAddUpdateViewModel: NoteAddUpdateViewModel

    private lateinit var binding: ActivityNoteAddUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        noteAddUpdateViewModel = obtainViewModel(this@NoteAddUpdateActivity)

        note = intent.getParcelableExtra(EXTRA_NOTE)
        if (note != null) {
            position = intent.getIntExtra(EXTRA_POSITION, 0)
            isEdit = true
        } else {
            note = Note()
        }

        val actionbarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionbarTitle = getString(R.string.change)
            btnTitle = getString(R.string.update)
            if (note != null) {
                note?.let { note ->
                    binding.edtTitle.setText(note.title)
                    binding.edtDescription.setText(note.description)
                }
            }
        } else {
            actionbarTitle = getString(R.string.add)
            btnTitle = getString(R.string.save)
        }

        supportActionBar?.title = actionbarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.text = btnTitle
        binding.btnSubmit.setOnClickListener {
            val title = binding.edtTitle.text.toString().trim()
            val description = binding.edtDescription.text.toString().trim()
            if (title.isEmpty()) {
                binding.edtTitle.error = getString(R.string.empty)
            } else if (description.isEmpty()) {
                binding.edtDescription.error = getString(R.string.empty)
            } else {
                note.let { note ->
                    note?.title = title
                    note?.description = description
                }

                val intent = Intent().apply {
                    putExtra(EXTRA_NOTE, note)
                    putExtra(EXTRA_POSITION, position)
                }

                if (isEdit) {
                    noteAddUpdateViewModel.update(note as Note)
                    setResult(RESULT_UPDATE, intent)
                    finish()
                } else {
                    note.let { note ->
                        note?.date = DateHelper.getCurrentDate()
                    }
                    noteAddUpdateViewModel.insert(note as Note)
                    setResult(RESULT_ADD, intent)
                    finish()
                }
            }
        }

    }

    private fun obtainViewModel(activity: AppCompatActivity): NoteAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(NoteAddUpdateViewModel::class.java)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_form, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        showAlertDialog(ALERT_DIALOG_CLOSE)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialog: String
        val dialogTitle: String
        val dialogMessage: String
        if (isDialogClose) {
            dialogTitle = getString(R.string.cancel)
            dialogMessage = getString(R.string.cancel)
        } else {
            dialogMessage = getString(R.string.message_delete)
            dialogTitle = getString(R.string.delete)
        }
        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton(getString(R.string.delete)) { _, _ ->
                if (!isDialogClose) {
                    noteAddUpdateViewModel.delete(note as Note)

                    val intent = Intent()
                    intent.putExtra(EXTRA_POSITION, position)
                    setResult(RESULT_DELETE, intent)
                }

                finish()

            }
            setNegativeButton(getString(R.string.no)) {dialog, _ ->
                dialog.cancel()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}