package com.openweathermaporg.myapplication.repository

import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponseRoom
import com.openweathermaporg.myapplication.data.dataclasses.db.Dao
import javax.inject.Inject

class RoomRepository @Inject constructor(private val dao: Dao) {
    fun insert(weatherResponse: WeatherResponseRoom) {
        dao.insert(weatherResponse)
    }

    fun load(name: String): WeatherResponseRoom? = dao.getCity(name)

    fun loadList(): List<WeatherResponseRoom> = dao.getList()
}