package com.nurlandroid.kotapp.networkbound

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData

@Suppress("LeakingThis")
abstract class NetworkBoundRepository<RESULTTYPE, REQUESTTYPE>
internal constructor() {
    private val resultMediator: MediatorLiveData<Resource<RESULTTYPE>> = MediatorLiveData()

    init {
        val loadedFromDB: LiveData<RESULTTYPE> = loadFromDB()
        resultMediator.addSource(loadedFromDB) { loadedData ->
            resultMediator.removeSource(loadedFromDB)

            if (shouldFetch(data)) {
                resultMediator.postValue(Resource.loading(null, null))
                fetchFromNetwork(loadedFromDB)
            } else {
                resultMediator.addSource<RESULTTYPE>(loadedFromDB) { newData ->
                    setValue(Resource.success(newData, 1))
                }
            }

        }
    }

    @MainThread
    private fun setValue(newValue: Resource<RESULTTYPE>) {
        resultMediator.value = newValue
    }

    fun asLiveData(): LiveData<Resource<RESULTTYPE>> = resultMediator

    @WorkerThread
    protected abstract fun saveFetchData(items: REQUESTTYPE)

    @MainThread
    protected abstract fun shouldFetch(data: RESULTTYPE?): Boolean

    @MainThread
    protected abstract fun loadFromDb(): LiveData<RESULTTYPE>

    @MainThread
    protected abstract fun fetchService(): LiveData<ApiResponse<REQUESTTYPE>>

    @MainThread
    protected abstract fun onFetchFailed(envelope: Envelope?)
}

