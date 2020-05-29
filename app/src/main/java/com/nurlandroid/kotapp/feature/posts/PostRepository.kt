package com.nurlandroid.kotapp.feature.posts

import com.nurlandroid.kotapp.NetworkApi

class PostRepository(private val service: NetworkApi) {

    suspend fun loadData(): List<Post> = service.getPosts().body() ?: emptyList()
}