package com.nurlandroid.kotapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nurlandroid.kotapp.feature.posts.Post
import com.nurlandroid.kotapp.feature.posts.PostDao

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun postsDao(): PostDao

    companion object {

        @Volatile
        private lateinit var INSTANCE: AppDatabase

        fun getDatabase(context: Context): AppDatabase {
            synchronized(AppDatabase::class.java) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java, "posts_database"
                )
                    .build()
            }
            return INSTANCE
        }
    }
}