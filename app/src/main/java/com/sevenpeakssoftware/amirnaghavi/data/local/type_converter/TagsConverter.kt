package com.sevenpeakssoftware.amirnaghavi.data.local.type_converter

import androidx.room.TypeConverter
import com.google.gson.Gson


class TagsConverter {
    @TypeConverter
    fun fromTagList(contents: List<String>): String = Gson().toJson(contents)

    @TypeConverter
    fun toTagList(values: String): List<String> =
        Gson().fromJson(values, Array<String>::class.java).toList()
}