package com.openweathermaporg.myapplication.data

import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponse
import com.openweathermaporg.myapplication.data.dataclasses.fivedayweather.FiveDayWeatherResponse
import retrofit2.http.GET
import retrofit2.Call;
import retrofit2.http.Query

interface WeatherService {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String): Call<WeatherResponse>

    @GET("forecast")
    fun getFiveDayWeather(
        @Query("q") q: String,
        @Query("units") units: String,
        @Query("lang") lang: String,
        @Query("appid") appId: String): Call<FiveDayWeatherResponse>
}