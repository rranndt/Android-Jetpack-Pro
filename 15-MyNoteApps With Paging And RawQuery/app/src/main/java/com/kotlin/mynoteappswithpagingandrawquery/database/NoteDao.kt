package com.kotlin.mynoteappswithpagingandrawquery.database

import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery

/**
 *@author Rizki Rian Anandita
 * Create By rizki
 */
@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(note: Note)

    @Update
    fun update(note: Note)

    @Delete
    fun delete(note: Note)

    @RawQuery(observedEntities = [Note::class])
    fun getAllNotes(query: SupportSQLiteQuery): DataSource.Factory<Int, Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(list: List<Note>)

}