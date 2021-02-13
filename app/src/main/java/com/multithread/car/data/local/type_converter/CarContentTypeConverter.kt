package com.multithread.car.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.multithread.car.data.local.data.LocalCarContent


class CarContentTypeConverter {
    @TypeConverter
    fun fromContentList(contents: List<LocalCarContent>): String = Gson().toJson(contents)

    @TypeConverter
    fun toContentList(values: String): List<LocalCarContent> =
        Gson().fromJson(values, Array<LocalCarContent>::class.java).toList()
}