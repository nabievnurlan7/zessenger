package com.nurlandroid.kotapp.networkbound

import androidx.lifecycle.LiveData
import com.nurlandroid.kotapp.coroutine.NetworkApi
import com.nurlandroid.kotapp.coroutine.Post

class PostsRepository(val api: NetworkApi) {

    fun loadPosts(page: Int): LiveData<Resource<List<Post>>> {
        return object : NetworkBoundRepository<List<Post>, List<Post>>() {
            override fun saveFetchedData(items: List<Post>) {
                // Write to DB in async mode
                for (post in items) {
                    post.page = page
                }
                // write save to DB function
            }

            override fun shouldFetch(data: List<Post>?): Boolean = data == null || data.isEmpty()

            override fun loadFromDb(): LiveData<List<Post>> {
                // return postsDao().loadPosts(page)
            }

            override fun fetchService(): LiveData<ApiResponse<List<Post>>> {
                 return api.fetchPagedPosts("user", page, PAGE_SIZE)
            }

            override fun onFetchFailed(envelope: Envelope?) {
            }
        }.asLiveData()
    }

    companion object {
        const val PAGE_SIZE = 10
    }
}