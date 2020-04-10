package com.nurlandroid.kotapp.coroutine

class DummyInteractor(private val repository: DummyRepository) {

    suspend fun doWork(): List<Post>? = repository.getPosts().loadedData
}