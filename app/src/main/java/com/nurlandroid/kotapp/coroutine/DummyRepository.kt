package com.nurlandroid.kotapp.coroutine

class DummyRepository(private val api: NetworkApi) {

    data class Result<T>(val loadedData: List<T>?)

    suspend fun getPosts(): Result<Post> {
        val result = api.getPosts()
        return Result(result.body())
    }
}