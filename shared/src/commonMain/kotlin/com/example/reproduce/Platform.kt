package com.example.reproduce

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform