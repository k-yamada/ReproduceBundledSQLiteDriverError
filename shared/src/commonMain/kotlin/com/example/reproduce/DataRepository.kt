package com.example.reproduce

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.example.reproduce.database.BookmarkRepositoryImpl
import com.example.reproduce.di.DatabaseFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

class DataRepository(
    val factory: DatabaseFactory,
) {
    val database = factory.getDatabaseBuilder()
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
    val bookmarkRepository = BookmarkRepositoryImpl(database.bookmarkDao())
}
