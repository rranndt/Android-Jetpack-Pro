package com.kotlin.mynoteappswithpaging.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.kotlin.mynoteappswithpaging.R
import com.kotlin.mynoteappswithpaging.database.Note
import com.kotlin.mynoteappswithpaging.databinding.ActivityMainBinding
import com.kotlin.mynoteappswithpaging.helper.ViewModelFactory
import com.kotlin.mynoteappswithpaging.ui.insert.NoteAddUpdateActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: NotePagedListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val mainViewModel = obtainViewModel(this@MainActivity)
        mainViewModel.getAllNotes().observe(this, noteObserver)
        adapter = NotePagedListAdapter(this@MainActivity)

        binding.rvNotes.layoutManager = LinearLayoutManager(this)
        binding.rvNotes.setHasFixedSize(true)
        binding.rvNotes.adapter = adapter

        binding.fabAdd.setOnClickListener { view ->
            if (view.id == R.id.fab_add) {
                val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
                startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD)
            }
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory).get(MainViewModel::class.java)
    }

    private val noteObserver = Observer<PagedList<Note>> { noteList ->
        if (noteList != null) {
            adapter.submitList(noteList)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == NoteAddUpdateActivity.REQUEST_ADD) {
                if (resultCode == NoteAddUpdateActivity.RESULT_ADD) {
                    showSnackBarMessage(getString(R.string.added))
                }
            } else if (requestCode == NoteAddUpdateActivity.REQUEST_UPDATE) {
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE) {
                    showSnackBarMessage(getString(R.string.change))
                } else if (resultCode == NoteAddUpdateActivity.RESULT_DELETE) {
                    showSnackBarMessage(getString(R.string.delete))
                }
            }
        }
    }

    private fun showSnackBarMessage(message: String) {
        Snackbar.make(binding.root as View, message, Snackbar.LENGTH_SHORT).show()
    }

}