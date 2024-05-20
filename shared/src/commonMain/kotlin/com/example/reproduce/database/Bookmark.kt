package com.example.reproduce.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.example.reproduce.model.BookmarkData

@Entity(
    tableName = "bookmarks",
    indices = [Index(value = ["url"])],
)
data class Bookmark(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "createdAt") val createdAt: Long
) {
    fun toData(): BookmarkData {
        return BookmarkData(
            id = id,
            name = name,
            url = url,
            createdAt = createdAt
        )
    }
}

fun BookmarkData.toBookmark(): Bookmark {
    return Bookmark(
        id = this.id,
        name = this.name,
        url = this.url,
        createdAt = this.createdAt
    )
}
