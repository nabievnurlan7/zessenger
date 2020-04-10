package com.nurlandroid.kotapp.coroutine

import retrofit2.Response
import retrofit2.http.GET

interface NetworkApi {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>
}