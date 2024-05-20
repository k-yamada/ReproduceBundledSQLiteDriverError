package com.example.reproduce.model

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable

@Serializable
data class BookmarkData(
    val id: Long = 0,
    val name: String = "",
    val url: String = "",
    val createdAt: Long = Clock.System.now().epochSeconds,
)
