package com.example.reproduce.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.reproduce.database.Bookmark

@Dao
interface BookmarkDao {
    @Insert
    suspend fun insert(bookmark: Bookmark): Long

    @Query("SELECT * FROM bookmarks")
    suspend fun getAll(): List<Bookmark>

    @Delete
    suspend fun delete(vararg bookmark: Bookmark)
}
