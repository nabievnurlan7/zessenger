package com.nurlandroid.kotapp.feature

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurlandroid.kotapp.common.BaseViewModel
import com.nurlandroid.kotapp.coroutine.DummyInteractor
import com.nurlandroid.kotapp.coroutine.Post

class MyViewModel(val dummyInteractor: DummyInteractor) : BaseViewModel() {

    private val data = MutableLiveData<List<Post>>()

    init {
        postSomeData()
    }

    fun getData(): LiveData<List<Post>> = data

    private fun postSomeData() {
        doWorkInIO {
            val result = dummyInteractor.doWork()
            data.postValue(result)
        }
    }
}