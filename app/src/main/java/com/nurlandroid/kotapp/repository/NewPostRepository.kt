package com.nurlandroid.kotapp.repository

import androidx.lifecycle.LiveData
import com.nurlandroid.kotapp.coroutine.NetworkApi
import com.nurlandroid.kotapp.feature.NetPost
import com.nurlandroid.kotapp.feature.Post
import com.nurlandroid.kotapp.feature.PostDatabase

class NewPostRepository(service: NetworkApi, private val database: PostDatabase) :
    BaseRepository<NetPost>(service) {

    override fun loadData(): LiveData<List<Post>> =
        fetchData(
            networkCall = { api.getPosts() },
            saveToDb = { database.postsDao().insertPosts(it) }
//            ,            loadFromDb = { database.postsDao().getPosts() }
        )
}