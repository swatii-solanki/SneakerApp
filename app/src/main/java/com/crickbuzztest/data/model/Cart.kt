package com.crickbuzztest.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey val id: String,
    val retailPrice: Double,
    val name: String,
    val imageUrl: String,
)
