package com.crickbuzztest.data.datasource.remote

import com.crickbuzztest.data.model.Sneaker
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    suspend fun fetchSneakerList(): List<Sneaker>
}