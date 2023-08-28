package com.crickbuzztest.data.datasource.repo

import com.crickbuzztest.data.model.*
import kotlinx.coroutines.flow.Flow

interface SneakerRepo {

    suspend fun fetchSneakerList(): Flow<List<Sneaker>>
    suspend fun fetchCartList(): Flow<List<Cart>>
    suspend fun saveCartItem(cartItem: Cart)
    suspend fun removeCartItem(cartItem: Cart)
    fun calculateCartPrice(): Flow<Int?>
    suspend fun fetchSneakerListFromRemote()
}