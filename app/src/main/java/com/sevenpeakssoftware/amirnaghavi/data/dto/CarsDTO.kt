package com.sevenpeakssoftware.amirnaghavi.data.dto


import com.google.gson.annotations.SerializedName
import com.sevenpeakssoftware.amirnaghavi.base.BaseEntity

data class CarsDTO(
    @SerializedName("content")
    val content: List<CarItemDTO>,
    @SerializedName("serverTime")
    val serverTime: Int,
    @SerializedName("status")
    val status: String
):BaseEntity