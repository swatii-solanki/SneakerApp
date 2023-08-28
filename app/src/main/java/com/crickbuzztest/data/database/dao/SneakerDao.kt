package com.crickbuzztest.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.crickbuzztest.data.model.Cart
import com.crickbuzztest.data.model.Sneaker
import kotlinx.coroutines.flow.Flow

@Dao
interface SneakerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSneakers(sneakers: List<Sneaker>)

    @Query("SELECT * FROM Sneakers")
    fun fetchSneakerList(): Flow<List<Sneaker>>

    @Query("SELECT * FROM Cart")
    fun fetchCartList(): Flow<List<Cart>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartData(sneakers: Cart)

    @Delete
    suspend fun deleteCartData(sneakers: Cart)

    @Query("SELECT SUM(retailPrice) FROM cart")
    fun calculateCartPrice(): Flow<Int?>
}