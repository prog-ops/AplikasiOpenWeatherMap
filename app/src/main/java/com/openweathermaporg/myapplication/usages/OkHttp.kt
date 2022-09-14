package com.openweathermaporg.myapplication.usages

import android.content.Context
import com.openweathermaporg.myapplication.usages.NetworkUsage.hasNetwork
import okhttp3.Cache
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

private const val CONNECTION_TIMEOUT_MS: Long = 60

object OkHttp {

    fun setOkHttpWithCaching(context: Context): OkHttpClient {
        val cacheSize = (5 * 1024 * 1024).toLong()
        val cache = Cache(context.cacheDir, cacheSize)
        return OkHttpClient.Builder()
            .cache(cache)
            .connectTimeout(
                CONNECTION_TIMEOUT_MS,
                TimeUnit.SECONDS
            )
            .addInterceptor { chain ->
                var request = chain.request()
                request = if (hasNetwork(context)!!)
                    request.newBuilder().header("Cache-Control", "public, max-age=" + 5).build()
                else
                    request.newBuilder().header(
                        "Cache-Control",
                        "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                    ).build()
                chain.proceed(request)
            }
            .build()
    }
}