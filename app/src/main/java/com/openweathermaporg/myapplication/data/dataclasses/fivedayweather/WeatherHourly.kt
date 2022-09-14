package com.openweathermaporg.myapplication.data.dataclasses.fivedayweather

import com.openweathermaporg.myapplication.data.dataclasses.Clouds
import com.openweathermaporg.myapplication.data.dataclasses.Weather

data class WeatherHourly(
    val dt: Int,
    val main: FiveDayMain,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: FiveDayWind,
    val visibility: Int,
    val pop: Double,
    val sys: FiveDaySys,
    val dt_txt: String
)
