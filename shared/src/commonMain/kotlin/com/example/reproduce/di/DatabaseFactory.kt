package com.example.reproduce.di


import androidx.room.RoomDatabase
import com.example.reproduce.database.AppDatabase

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DatabaseFactory {
    fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
}
