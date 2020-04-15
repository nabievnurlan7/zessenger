package com.nurlandroid.kotapp.networkbound

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

@Suppress("LeakingThis")
abstract class NetworkBoundRepository<RESULTTYPE, REQUESTTYPE>
internal constructor() {
    private val resultMediator: MediatorLiveData<Resource<RESULTTYPE>> = MediatorLiveData()
//
//    init {
//        val loadedFromDB: LiveData<RESULTTYPE> = loadFromDb()
//        resultMediator.addSource(loadedFromDB) { loadedData ->
//            resultMediator.removeSource(loadedFromDB)
//
//            if (shouldFetch(loadedData)) {
//                resultMediator.postValue(Resource.loading(null, null))
//                fetchFromNetwork(loadedFromDB)
//            } else {
//                resultMediator.addSource<RESULTTYPE>(loadedFromDB) { newData ->
//                    setValue(Resource.success(newData, 1))
//                }
//            }
//
//        }
//    }
//
//    private fun fetchFromNetwork(loadedFromDb: LiveData<RESULTTYPE>) {
//        val apiResponse = fetchService()
//
//        resultMediator.addSource(apiResponse) { response ->
//            response?.let {
//                when (response.isSuccessful) {
//                    true -> {
//
//                        response.body()?.let {
//                            saveFetchedData(it)
//                            val loaded = loadFromDb()
//                            resultMediator.addSource(loaded) { newData ->
//                                newData?.let {
//                                    setValue(Resource.success(it, 1))
//                                }
//                            }
//                        }
//                    }
//                    false -> {
//                        resultMediator.removeSource(loadedFromDb)
//                        //onFetchFailed(response.envelope)
////                        response.envelope?.let {
////                            resultMediator.addSource(loadedFromDb) { oldData ->
////                                setValue(Resource.error(oldData, it.message))
////                            }
////                        }
//                    }
//                }
//            }
//
//        }
//    }
//
//    @MainThread
//    private fun setValue(newValue: Resource<RESULTTYPE>) {
//        resultMediator.value = newValue
//    }
//
//    fun asLiveData(): LiveData<Resource<RESULTTYPE>> = resultMediator
//
//    @WorkerThread
//    protected abstract fun saveFetchedData(items: REQUESTTYPE)
//
//    @MainThread
//    protected abstract fun shouldFetch(data: RESULTTYPE?): Boolean
//
////    @MainThread
////    protected abstract fun loadFromDb(): LiveData<RESULTTYPE>
//
//    @MainThread
//    protected abstract fun fetchService(): LiveData<Response<REQUESTTYPE>>
//
//    @MainThread
//    protected abstract fun onFetchFailed(envelope: Envelope?)
}

