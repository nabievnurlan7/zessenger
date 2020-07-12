package com.nurlandroid.kotapp.common

class RepositoryResult<out T>(
        val status: ResponseStatus,
        val fetchedData: T?,
        val message: String,
        val pageStatus: PageLoadStatus?
) {
    companion object {
        fun <T> success(fetchedData: T?, pageStatus: PageLoadStatus?): RepositoryResult<T> =
                RepositoryResult(status = ResponseStatus.SUCCESS, fetchedData = fetchedData, message = "", pageStatus = pageStatus)

        fun <T> error(errorMessage: String): RepositoryResult<T> =
                RepositoryResult(status = ResponseStatus.ERROR, fetchedData = null, message = errorMessage, pageStatus = null)
    }
}