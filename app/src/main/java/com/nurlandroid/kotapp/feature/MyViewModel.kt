package com.nurlandroid.kotapp.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurlandroid.kotapp.common.BaseViewModel
import com.nurlandroid.kotapp.repository.NewPostRepository

class MyViewModel(private val newPostRepository: NewPostRepository) : BaseViewModel() {

    private val data = MutableLiveData<List<Post>>()

    init {
        postSomeData()
    }

    fun getData(): LiveData<List<Post>> = newPostRepository.loadData()

    private fun postSomeData() {
//        doWorkInIO {
//            val result = dummyInteractor.doWork()
//            data.postValue(result)
//        }
    }
}