package com.example.reproduce.model

interface BookmarkRepository {
    suspend fun insert(bookmarkData: BookmarkData)
    suspend fun all(): List<BookmarkData>
    suspend fun delete(bookmarkData: BookmarkData)
}
