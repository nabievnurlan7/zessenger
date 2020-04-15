package com.nurlandroid.kotapp.feature

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(followers: List<Post>)

    @Query("SELECT * FROM Post")
    fun getPosts(): LiveData<List<Post>>
}