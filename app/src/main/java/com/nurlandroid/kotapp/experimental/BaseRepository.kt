package com.nurlandroid.kotapp.experimental

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nurlandroid.kotapp.NetworkApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.await
import timber.log.Timber

// Experimental code!!!
abstract class BaseRepository<T>(@PublishedApi internal val api: NetworkApi) {

    abstract fun loadData(): LiveData<List<T>>

    inline fun <reified T : Any> fetchData(
        crossinline networkCall: (NetworkApi) -> Call<Response<List<T>>>,
        crossinline saveToDb: (List<T>) -> Unit
    ): LiveData<List<T>> {
        val result = MutableLiveData<List<T>>()

        CoroutineScope(Dispatchers.IO).launch {
            val request = networkCall(api)
            var myList = mutableListOf<T>()
            try {
                val response = request.await()

                if (response.isSuccessful) {
                    response.body()?.let {
                        saveToDb(it)
                        myList = it as MutableList<T>
                    }
                } else {
                    Timber.d("Error occurred with code ${response.code()}")
                }
            } catch (e: Throwable) {
                Timber.d("Error: ${e.message}")
            }

            withContext(Dispatchers.Main) {
                result.value = myList
            }
        }

        return result
    }
}