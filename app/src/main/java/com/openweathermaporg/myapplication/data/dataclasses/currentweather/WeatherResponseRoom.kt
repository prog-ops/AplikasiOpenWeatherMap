package com.openweathermaporg.myapplication.data.dataclasses.currentweather

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weatherResponseTable")
data class WeatherResponseRoom(
    @PrimaryKey
    val id: Int,
    val name: String,
    val desc: String,
)