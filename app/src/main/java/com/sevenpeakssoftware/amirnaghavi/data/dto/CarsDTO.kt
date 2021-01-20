package com.sevenpeakssoftware.amirnaghavi.data.dto


import com.google.gson.annotations.SerializedName

data class CarsDTO(
    @SerializedName("content")
    val content: List<CarItemDTO>,
    @SerializedName("serverTime")
    val serverTime: Int,
    @SerializedName("status")
    val status: String
)