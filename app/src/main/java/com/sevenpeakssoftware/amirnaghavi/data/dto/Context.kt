package com.sevenpeakssoftware.amirnaghavi.data.dto


import com.google.gson.annotations.SerializedName

data class Context(
    @SerializedName("type")
    val type: String = "",
    @SerializedName("subject")
    val subject: String = "",
    @SerializedName("description")
    val description: String = ""
)