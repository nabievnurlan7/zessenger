package com.nurlandroid.kotapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by nurik on 25.06.2019.
 */
class MyViewModel : ViewModel() {

    val data = MutableLiveData<Item>()

    init {
        postSomeData()
    }

    fun getData(): LiveData<Item> = data

    private fun postSomeData() {
        data.postValue(Item("1", "name"))
    }
}