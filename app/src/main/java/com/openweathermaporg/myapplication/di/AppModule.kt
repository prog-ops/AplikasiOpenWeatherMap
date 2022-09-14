package com.openweathermaporg.myapplication.di

import android.app.Application
import com.openweathermaporg.myapplication.data.dataclasses.db.DB2
import com.openweathermaporg.myapplication.data.dataclasses.db.Dao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideDao(db: DB2): Dao {
        return db.dao()
    }

    @Provides
    @Singleton
    fun providesDb(context: Application): DB2 = DB2.getDb(context)
}

