package com.crickbuzztest.data.datasource.remote

import android.content.Context
import com.crickbuzztest.data.model.Sneaker
import com.crickbuzztest.utils.JsonUtil
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val context: Context) : RemoteDataSource {

    override suspend fun fetchSneakerList(): List<Sneaker> {
        return JsonUtil.getSneakersList(context)
    }
}