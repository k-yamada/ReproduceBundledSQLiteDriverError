package com.example.reproduce.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.reproduce.database.AppDatabase
import com.example.reproduce.database.dbFileName

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DatabaseFactory(private val app: Application) {
    actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFile = app.getDatabasePath(dbFileName)
        return Room.databaseBuilder<AppDatabase>(app, dbFile.absolutePath)
    }
}
