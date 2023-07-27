package com.example.multipleviewrecyclerview

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     suspend fun insertNews(article:List<Article>)

    @Query("SELECT * FROM NewsTable")
    fun getNews():List<Article>
}