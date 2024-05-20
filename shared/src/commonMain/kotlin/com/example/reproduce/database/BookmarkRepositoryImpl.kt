package com.example.reproduce.database

import com.example.reproduce.model.BookmarkData
import com.example.reproduce.model.BookmarkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BookmarkRepositoryImpl(private val dao: BookmarkDao) : BookmarkRepository {
    override suspend fun insert(bookmarkData: BookmarkData) {
        withContext(Dispatchers.Default) {
            dao.insert(bookmarkData.toBookmark().copy(id = 0))
        }
    }

    override suspend fun all(): List<BookmarkData> {
        return withContext(Dispatchers.Default) {
             dao.getAll().map { it.toData() }
        }
    }

    override suspend fun delete(bookmarkData: BookmarkData) {
        withContext(Dispatchers.Default) {
            dao.delete(bookmarkData.toBookmark())
        }
    }
}
