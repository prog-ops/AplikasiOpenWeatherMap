package com.openweathermaporg.myapplication.data.dataclasses.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponseRoom

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    fun insert(name: String)
    fun insert(weatherResponse: WeatherResponseRoom)

    @Query("SELECT * FROM weatherResponseTable WHERE name = :cityName")
    fun getCity(cityName: String): WeatherResponseRoom?

    @Query("SELECT * FROM weatherResponseTable")
    fun getList(): List<WeatherResponseRoom>
}