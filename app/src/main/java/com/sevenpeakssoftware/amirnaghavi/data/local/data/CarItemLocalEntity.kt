package com.sevenpeakssoftware.amirnaghavi.data.local.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sevenpeakssoftware.amirnaghavi.data.dto.ContentDTO

@Entity(tableName = "cars")
data class CarItemLocalEntity(
        val changed: Int,
        val content: List<ContentDTO>,
        val created: Int,
        val dateTime: String,
        @PrimaryKey
        val id: Int,
        val image: String,
        val ingress: String,
        val tags: List<String>,
        val title: String
) {
    data class LocalCarContent(
            val description: String,
            val subject: String,
            val type: String
    )
}