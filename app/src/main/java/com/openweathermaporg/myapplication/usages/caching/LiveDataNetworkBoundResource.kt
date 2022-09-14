package com.openweathermaporg.myapplication.usages.caching

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map

abstract class LiveDataNetworkBoundResource<T> {

    fun asLiveData() = liveData<Resource<T>> {
        emit(Resource.Loading(null))

        if (shouldFetch(query())) {
            val disposable = emitSource(queryObservable().map { Resource.Loading(it) })

            try {
                val fetchedData = fetch()
                disposable.dispose()
                saveFetchResult(fetchedData)
                emitSource(queryObservable().map { Resource.Sukses(it) })

            } catch (throwable: Exception) {
                onFetchFailed(throwable)
                emitSource(queryObservable().map { Resource.Error(throwable, it) })
            }
        } else {
            emitSource(queryObservable().map { Resource.Sukses(it) })
        }
    }

    abstract suspend fun query(): T
    abstract fun queryObservable(): LiveData<T>
    abstract suspend fun fetch(): T
    abstract suspend fun saveFetchResult(data: T)
    open fun onFetchFailed(exception: Exception) = Unit
    open fun shouldFetch(data: T) = true
}