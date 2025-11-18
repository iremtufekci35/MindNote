package com.example.mindnote.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.mindnote.local.entity.Note

@Dao
interface NoteDao {
    @Insert
    fun insert(note: Note)

    @Delete
    fun delete(note: Note)

    @Query("SELECT*FROM notes")
    fun getAllNotes(): List<Note>
}