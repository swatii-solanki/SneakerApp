package com.crickbuzztest.data.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Sneakers")
@Parcelize
data class Sneaker(
    @PrimaryKey val id: String,
    val brand: String,
    val colorway: String,
    val gender: String,
    val releaseDate: String,
    val retailPrice: Double,
    val styleId: String,
    val shoe: String,
    val name: String,
    val title: String,
    val year: Int,
    val imageUrl: String,
): Parcelable
