package com.nurlandroid.kotapp.coroutine

import androidx.lifecycle.LiveData
import com.nurlandroid.kotapp.networkbound.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NetworkApi {

    @GET("posts")
    suspend fun getPosts(): Response<List<Post>>

    @GET("/users/{user}/followers")
    fun fetchPagedPosts(@Path("user") user: String, @Query("page") page: Int, @Query("per_page") per_page: Int): LiveData<ApiResponse<List<Post>>>
}