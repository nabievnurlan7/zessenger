package com.nurlandroid.kotapp.feature

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Post::class], version = 1, exportSchema = false)
abstract class PostDatabase : RoomDatabase() {

    abstract fun postsDao(): PostsDao

    companion object {

        @Volatile
        private lateinit var INSTANCE: PostDatabase

        fun getDatabase(context: Context): PostDatabase {
            synchronized(PostDatabase::class.java) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    PostDatabase::class.java, "posts_database"
                )
                    .build()
            }
            return INSTANCE
        }
    }
}