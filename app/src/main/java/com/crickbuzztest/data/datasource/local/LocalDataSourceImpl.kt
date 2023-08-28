package com.crickbuzztest.data.datasource.local

import com.crickbuzztest.data.database.dao.SneakerDao
import com.crickbuzztest.data.model.Cart
import com.crickbuzztest.data.model.Sneaker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(private val dao: SneakerDao) : LocalDataSource {

    override suspend fun insertSneakerData(sneakers: List<Sneaker>) {
        dao.insertSneakers(sneakers)
    }

    override suspend fun fetchSneakerList(): Flow<List<Sneaker>> {
        return dao.fetchSneakerList()
    }

    override suspend fun fetchCartList(): Flow<List<Cart>> {
        return dao.fetchCartList()
    }

    override suspend fun saveCartItem(cartItem: Cart) {
        dao.insertCartData(cartItem)
    }

    override suspend fun deleteCartItem(cartItem: Cart) {
        dao.deleteCartData(cartItem)
    }

    override fun calculateCartPrice(): Flow<Int?> {
        return dao.calculateCartPrice()
    }
}