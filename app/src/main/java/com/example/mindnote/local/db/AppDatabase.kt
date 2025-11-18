package com.example.mindnote.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mindnote.local.entity.Note
import com.example.mindnote.local.dao.NoteDao

@Database(
    entities = [Note::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao
}
