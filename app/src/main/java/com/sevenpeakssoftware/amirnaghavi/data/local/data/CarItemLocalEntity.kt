package com.sevenpeakssoftware.amirnaghavi.data.local.data

import androidx.room.Entity
import com.sevenpeakssoftware.amirnaghavi.AppConstant

@Entity(tableName = AppConstant.CARS_TABLE_NAME, primaryKeys = ["id"])
data class CarItemLocalEntity(
        val changed: Int,
        val content: List<LocalCarContent>,
        val created: Int,
        val dateTime: String,
        val id: Int,
        val image: String,
        val ingress: String,
        val title: String
)

data class LocalCarContent(
        val description: String,
        val subject: String,
        val type: String
)