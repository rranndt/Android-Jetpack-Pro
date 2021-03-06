package com.kotlin.mynoteappswithpagingandrawquery.repository

import android.app.Application
import androidx.paging.DataSource
import com.kotlin.mynoteappswithpagingandrawquery.database.Note
import com.kotlin.mynoteappswithpagingandrawquery.database.NoteDao
import com.kotlin.mynoteappswithpagingandrawquery.database.NoteRoomDatabase
import com.kotlin.mynoteappswithpagingandrawquery.helper.SortUtils
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
class NoteRepository(application: Application) {

    private val mNotesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteRoomDatabase.getDatabase(application)
        mNotesDao = db.noteDao()
    }

    fun getAllNotes(sort: String): DataSource.Factory<Int, Note> {
        val query = SortUtils.getSortedQuery(sort)
        return mNotesDao.getAllNotes(query)
    }

    fun insert(note: Note) {
        executorService.execute { mNotesDao.insert(note) }
    }

    fun delete(note: Note) {
        executorService.execute { mNotesDao.delete(note) }
    }

    fun update(note: Note) {
        executorService.execute { mNotesDao.update(note) }
    }

}