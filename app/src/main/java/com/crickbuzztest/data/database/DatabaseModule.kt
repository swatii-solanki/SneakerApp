package com.crickbuzztest.data.database

import android.content.Context
import androidx.room.Room
import com.crickbuzztest.data.converters.Converter
import com.crickbuzztest.data.database.dao.SneakerDao
import com.crickbuzztest.data.database.db.SneakerDB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideSneakerDao(sneakerDB: SneakerDB): SneakerDao {
        return sneakerDB.sneakerDao()
    }

    @Provides
    @Singleton
    fun provideSneakerDB(@ApplicationContext context: Context): SneakerDB {
        return Room.databaseBuilder(context, SneakerDB::class.java, "sneaker_db").build()
    }
}