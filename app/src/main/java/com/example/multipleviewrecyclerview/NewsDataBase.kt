package com.example.multipleviewrecyclerview

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Article::class], version = 2)

 abstract class NewsDataBase : RoomDatabase() {

    abstract fun NewsDao (): RoomDao
    companion object {
        private var INTANCE: NewsDataBase? = null

        fun getDatabase(context: Context): NewsDataBase {

            if (INTANCE == null) {
                INTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    NewsDataBase::class.java,
                    "NewsDB"
                ).fallbackToDestructiveMigration().build()
            }
            return INTANCE!!
        }
    }
}