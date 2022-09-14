package com.openweathermaporg.myapplication.usages.caching

import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import kotlinx.coroutines.flow.*

inline fun <TipeResult, TipeRequest> networkBoundResourceUsingFlow(
    crossinline kueri: () -> Flow<TipeResult>,
    crossinline fetch: suspend () -> TipeRequest,
    crossinline simpanHasilFetch: suspend (TipeRequest) -> Unit,
    crossinline apakahHarusFetch: (TipeResult) -> Boolean = { true }
) = flow {
    val data = kueri().first()

    val flow =
        if (apakahHarusFetch(data)) {
            emit(Resource.Loading(data))

            try {
                simpanHasilFetch(fetch())
                kueri().map {
                    Resource.Sukses(it)
                }
            } catch (throwable: Throwable) {
                kueri().map {
                    Resource.Sukses(it)
                }
            }
        } else {
            kueri().map {
                Resource.Sukses(it)
            }
        }

    emitAll(flow)
}

inline fun <TipeResult, TipeRequest> networkBoundResourceUsingLiveData(
    crossinline kueri: () -> Flow<TipeResult>,
    crossinline fetch: suspend () -> TipeRequest,
    crossinline simpanHasilFetch: suspend (TipeRequest) -> Unit,
    crossinline apakahHarusFetch: (TipeResult) -> Boolean = { true }
) = liveData {
    val data = kueri().first()

    val liveData =
        if (apakahHarusFetch(data)) {
            emit(Resource.Loading(data))

            try {
                simpanHasilFetch(fetch())
                kueri().map {
                    Resource.Sukses(it)
                }
            } catch (throwable: Throwable) {
                kueri().map {
                    Resource.Sukses(it)
                }
            }
        } else {
            kueri().map {
                Resource.Sukses(it)
            }
        }

    emitSource(liveData.asLiveData())
}