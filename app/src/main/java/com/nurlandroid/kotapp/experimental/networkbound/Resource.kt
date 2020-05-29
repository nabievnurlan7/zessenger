package com.nurlandroid.kotapp.experimental.networkbound

class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val fetchStatus: FetchStatus
) {

    companion object {
        private var fetchStatus = FetchStatus()

        fun <T> success(data: T?, nextPage: Int?): Resource<T> {
            fetchStatus = FetchStatus(isOnLoading = false, isOnSuccess = true, isOnError = false, isOnLast = false, nextPage = nextPage)
            if (nextPage == null) fetchStatus = FetchStatus(isOnLoading = false, isOnSuccess = true, isOnError = false, isOnLast = true, nextPage = nextPage)
            return Resource(Status.SUCCESS, data, null, fetchStatus)
        }

        fun <T> loading(data: T?, nextPage: Int?): Resource<T> {
            fetchStatus = FetchStatus(isOnLoading = true, isOnSuccess = false, isOnError = false, isOnLast = false, nextPage = nextPage)
            return Resource(Status.LOADING, data, null, fetchStatus)
        }

        fun <T> error(data: T?, msg: String): Resource<T> {
            fetchStatus = FetchStatus(isOnLoading = false, isOnSuccess = false, isOnError = true, isOnLast = true)
            return Resource(Status.ERROR, data, msg, fetchStatus)
        }
    }
}