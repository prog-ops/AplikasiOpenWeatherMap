package com.openweathermaporg.myapplication.data.dataclasses.currentweather

import com.openweathermaporg.myapplication.data.dataclasses.Clouds
import com.openweathermaporg.myapplication.data.dataclasses.Coord
import com.openweathermaporg.myapplication.data.dataclasses.Weather

data class WeatherResponse(
    val coord: Coord,
    val weather: List<Weather>,
    val base: String,
    val main: Main,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Int,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)