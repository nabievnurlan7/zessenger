package com.nurlandroid.kotapp.feature.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurlandroid.kotapp.common.BaseViewModel

class PostViewModel(private val postRepository: PostRepository) : BaseViewModel() {

    private val mutableLiveData = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> = mutableLiveData

    init {
        loadSomeData()
    }

    private fun loadSomeData() {
        doWorkInMainThread {
            val result = postRepository.loadData()
            mutableLiveData.postValue(result)
        }
    }
}