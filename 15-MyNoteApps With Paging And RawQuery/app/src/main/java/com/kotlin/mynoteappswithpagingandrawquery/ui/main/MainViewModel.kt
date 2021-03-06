package com.kotlin.mynoteappswithpagingandrawquery.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.kotlin.mynoteappswithpagingandrawquery.database.Note
import com.kotlin.mynoteappswithpagingandrawquery.repository.NoteRepository

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class MainViewModel(application: Application) : ViewModel() {

    private val mNoteRepository: NoteRepository =
        NoteRepository(application)

    fun getAllNotes(sort: String): LiveData<PagedList<Note>> =
        LivePagedListBuilder(mNoteRepository.getAllNotes(sort), 20).build()

}