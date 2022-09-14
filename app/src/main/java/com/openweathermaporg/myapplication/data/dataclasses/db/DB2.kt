package com.openweathermaporg.myapplication.data.dataclasses.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.openweathermaporg.myapplication.data.dataclasses.currentweather.WeatherResponseRoom

@Database(entities = [WeatherResponseRoom::class], version = 1)
abstract class DB2 : RoomDatabase() {
    abstract fun dao(): Dao

    companion object {
        private const val DB_NAME = "databs"

        private var INSTANCE: DB2? = null

        fun getDb(context: Context): DB2 {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    DB2::class.java,
                    DB_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE!!
        }
    }
}