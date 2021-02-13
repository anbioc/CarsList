package com.multithread.car.data.dto


import com.google.gson.annotations.SerializedName

data class CarDTO(
    @SerializedName("status")
    val status: String = "",
    @SerializedName("content")
    val content: List<CarItemContent> = emptyList(),
    @SerializedName("serverTime")
    val serverTime: Int = 0
)