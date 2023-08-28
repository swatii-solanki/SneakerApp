package com.crickbuzztest.data.datasource.local

import com.crickbuzztest.data.model.Cart
import com.crickbuzztest.data.model.Sneaker
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    suspend fun insertSneakerData(sneakers: List<Sneaker>)
    suspend fun fetchSneakerList(): Flow<List<Sneaker>>
    suspend fun fetchCartList(): Flow<List<Cart>>
    suspend fun saveCartItem(cartItem: Cart)
    suspend fun deleteCartItem(cartItem: Cart)
    fun calculateCartPrice(): Flow<Int?>
}