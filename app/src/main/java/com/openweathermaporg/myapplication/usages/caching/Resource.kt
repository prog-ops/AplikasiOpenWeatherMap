package com.openweathermaporg.myapplication.usages.caching

sealed class Resource<A>(
    val theData: A? = null,
    val error: Throwable? = null
) {
    class Sukses<A>(data: A)            : Resource<A>(data)
    class Loading<A>(data: A? = null)   : Resource<A>(data)
    class Error<A>(
        throwable: Throwable,
        data: A? = null)                : Resource<A>(data, throwable)
}
