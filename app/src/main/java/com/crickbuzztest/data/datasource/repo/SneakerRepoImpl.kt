package com.crickbuzztest.data.datasource.repo

import com.crickbuzztest.data.datasource.local.LocalDataSource
import com.crickbuzztest.data.datasource.remote.RemoteDataSource
import com.crickbuzztest.data.model.Cart
import com.crickbuzztest.data.model.Sneaker
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SneakerRepoImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : SneakerRepo {

    override suspend fun fetchSneakerList(): Flow<List<Sneaker>> =
        localDataSource.fetchSneakerList()

    override suspend fun fetchCartList(): Flow<List<Cart>> =
        localDataSource.fetchCartList()

    override suspend fun saveCartItem(cartItem: Cart) {
        localDataSource.saveCartItem(cartItem)
    }

    override suspend fun removeCartItem(cartItem: Cart) {
        localDataSource.deleteCartItem(cartItem)
    }

    override fun calculateCartPrice(): Flow<Int?> {
        return localDataSource.calculateCartPrice()
    }

    override suspend fun fetchSneakerListFromRemote() {
        localDataSource.insertSneakerData(remoteDataSource.fetchSneakerList())
    }
}