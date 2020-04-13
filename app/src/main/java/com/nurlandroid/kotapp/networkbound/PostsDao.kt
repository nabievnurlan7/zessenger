package com.nurlandroid.kotapp.networkbound

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nurlandroid.kotapp.coroutine.Post

@Dao
interface PostsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(followers: List<Post>)

    @Query("SELECT * FROM Post WHERE page = :pageParam")
    fun getPosts(pageParam: Int): LiveData<List<Post>>
}