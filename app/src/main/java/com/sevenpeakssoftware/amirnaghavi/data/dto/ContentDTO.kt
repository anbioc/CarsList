package com.sevenpeakssoftware.amirnaghavi.data.dto


import com.google.gson.annotations.SerializedName

data class ContentDTO(
    @SerializedName("description")
    val description: String,
    @SerializedName("subject")
    val subject: String,
    @SerializedName("type")
    val type: String
)