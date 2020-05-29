package com.nurlandroid.kotapp.feature.posts

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.nurlandroid.kotapp.feature.posts.Post

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(followers: List<Post>)

}