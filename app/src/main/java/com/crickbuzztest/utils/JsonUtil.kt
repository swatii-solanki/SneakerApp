package com.crickbuzztest.utils

import android.content.Context
import com.crickbuzztest.data.model.Sneaker
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object JsonUtil {

    fun getSneakersList(context: Context): List<Sneaker> {
        val gson = Gson()
        return try {
            val fileReader = context.assets.open("sneakers_list.json").bufferedReader().use { it.readText() }
            val type = object : TypeToken<List<Sneaker>>() {}.type
            val data = gson.fromJson<List<Sneaker>>(fileReader, type)
            data ?: emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}