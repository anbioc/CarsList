package com.multithread.car.data.dto


import com.google.gson.annotations.SerializedName

data class CarItemContent(
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("title")
    val title: String = "",
    @SerializedName("dateTime")
    val dateTime: String = "",
    @SerializedName("content")
    val content: List<Context> = emptyList(),
    @SerializedName("ingress")
    val ingress: String = "",
    @SerializedName("image")
    val image: String = "",
    @SerializedName("created")
    val created: Int = 0,
    @SerializedName("changed")
    val changed: Int = 0
)