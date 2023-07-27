package com.example.multipleviewrecyclerview

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NewsTable")
data class Article(
    @PrimaryKey
    val title: String,
    var description: String?=null,
    val url: String,
    var urlToImage: String?=null,
)