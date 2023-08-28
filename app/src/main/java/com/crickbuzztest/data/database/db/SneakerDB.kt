package com.crickbuzztest.data.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.crickbuzztest.data.database.dao.SneakerDao
import com.crickbuzztest.data.model.Cart
import com.crickbuzztest.data.model.Sneaker

@Database(
    entities = [Sneaker::class, Cart::class],
    version = 1,
    exportSchema = false
)
abstract class SneakerDB : RoomDatabase() {
    abstract fun sneakerDao(): SneakerDao
}