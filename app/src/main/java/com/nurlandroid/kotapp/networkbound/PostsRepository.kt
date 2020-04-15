package com.nurlandroid.kotapp.networkbound

import com.nurlandroid.kotapp.feature.PostDatabase
import com.nurlandroid.kotapp.coroutine.NetworkApi

class PostsRepository(
    val database: PostDatabase,
    val api: NetworkApi
) {
//    fun loadPosts(page: Int): LiveData<Resource<List<Post>>> {
//        return object : NetworkBoundRepository<List<Post>, List<Post>>() {
//
//            override fun fetchService(): LiveData<Response<List<Post>>> {
//                val liveData = MutableLiveData<Response<List<Post>>>()
////                api.getPosts()
//                return liveData
//            }
//
//            override fun saveFetchedData(items: List<Post>) {
//                database.runInTransaction {
//                    for (post in items) {
//                        post.page = page
//                    }
//                    database.postsDao().insertPosts(items)
//                }
//            }
//
//            override fun shouldFetch(data: List<Post>?): Boolean =
//                data == null || data.isEmpty()
//
//
//
//            override fun onFetchFailed(envelope: Envelope?) {
//                Timber.d("onFetchFailed : $envelope")
//            }
//        }.asLiveData()
//    }

    companion object {
        const val PAGE_SIZE = 10
    }
}