package com.openweathermaporg.myapplication.data.dataclasses.currentweather

data class Sys(
    val type: Int,
    val id: Int,
    val message: Double,
    val country: String,
    val sunrise: Int,
    val sunset: Int,
)