package com.nurlandroid.kotapp.networkbound

import androidx.lifecycle.LiveData
import com.nurlandroid.kotapp.coroutine.NetworkApi
import com.nurlandroid.kotapp.coroutine.Post
import timber.log.Timber

class PostsRepository(
    val database: PostDatabase,
    val api: NetworkApi
) {
    fun loadPosts(page: Int): LiveData<Resource<List<Post>>> {
        return object : NetworkBoundRepository<List<Post>, List<Post>>() {
            override fun saveFetchedData(items: List<Post>) {
                database.runInTransaction {
                    for (post in items) {
                        post.page = page
                    }
                    database.postsDao().insertPosts(items)
                }
            }

            override fun shouldFetch(data: List<Post>?): Boolean =
                data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<Post>> =
                database.postsDao().getPosts(page)

            override fun fetchService(): LiveData<ApiResponse<List<Post>>> =
                api.fetchPagedPosts("user", page, PAGE_SIZE)

            override fun onFetchFailed(envelope: Envelope?) {
                Timber.d("onFetchFailed : $envelope")
            }
        }.asLiveData()
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}