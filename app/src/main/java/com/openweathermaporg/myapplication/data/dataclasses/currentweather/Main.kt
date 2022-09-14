package com.openweathermaporg.myapplication.data.dataclasses.currentweather

data class Main(
    val temp: Double,
    val feelsLike: Double,
    val tempMax: Double,
    val tempMin: Double,
    val pressure: Double,
    val humidity: Int
)
