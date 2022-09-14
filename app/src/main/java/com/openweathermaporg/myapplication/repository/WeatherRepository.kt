package com.openweathermaporg.myapplication.repository

import com.openweathermaporg.myapplication.BuildConfig
import com.openweathermaporg.myapplication.data.WebClient
import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponse
import com.openweathermaporg.myapplication.data.dataclasses.fivedayweather.FiveDayWeatherResponse
import javax.inject.Inject
import javax.inject.Singleton

private const val UNITS = "metric"
private const val LANGUAGE = "en"

@Singleton
class WeatherRepository @Inject constructor() {

    fun getCurrentWeather(cityName: String): WeatherResponse? {
        val currentWeather: WeatherResponse?

        val response = WebClient.getClient().getCurrentWeather(cityName, UNITS, LANGUAGE, BuildConfig.weather_api).execute()
        currentWeather = response.body()
        return currentWeather
    }

    fun getFiveDayWeather(cityName: String): FiveDayWeatherResponse? {
        val currentWeather: FiveDayWeatherResponse?

        val response = WebClient.getClient().getFiveDayWeather(cityName, UNITS, LANGUAGE, BuildConfig.weather_api).execute()
        currentWeather = response.body()
        return currentWeather
    }
}