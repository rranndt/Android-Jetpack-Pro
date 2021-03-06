package com.kotlin.mynoteappswithpagingandrawquery.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.kotlin.mynoteappswithpagingandrawquery.database.Note
import com.kotlin.mynoteappswithpagingandrawquery.repository.NoteRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class NoteAddUpdateViewModel(application: Application) : ViewModel() {

    private val mNoteRepository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        mNoteRepository.insert(note)
    }

    fun update(note: Note) {
        mNoteRepository.update(note)
    }

    fun delete(note: Note) {
        mNoteRepository.delete(note)
    }

}