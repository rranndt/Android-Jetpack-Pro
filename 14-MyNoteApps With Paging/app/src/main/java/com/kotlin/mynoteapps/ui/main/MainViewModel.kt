package com.kotlin.mynoteapps.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kotlin.mynoteapps.database.Note
import com.kotlin.mynoteapps.repository.NoteRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MainViewModel(application: Application) : ViewModel() {

    private val mNoteRepository: NoteRepository =
        NoteRepository(application)

    fun getAllNotes(): LiveData<PagedList<Note>> =
        LivePagedListBuilder(mNoteRepository.getAllNotes(), 20).build()

}