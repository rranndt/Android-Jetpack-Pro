package com.kotlin.mynoteapps.database

import androidx.lifecycle.LiveData
import androidx.room.*

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

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun getAllNotes(): LiveData<List<Note>>

}