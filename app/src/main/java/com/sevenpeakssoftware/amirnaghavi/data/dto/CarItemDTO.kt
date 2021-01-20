package com.sevenpeakssoftware.amirnaghavi.data.dto


import com.google.gson.annotations.SerializedName

data class CarItemDTO(
    @SerializedName("changed")
    val changed: Int,
    @SerializedName("content")
    val content: List<ContentDTO>,
    @SerializedName("created")
    val created: Int,
    @SerializedName("dateTime")
    val dateTime: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("ingress")
    val ingress: String,
    @SerializedName("tags")
    val tags: List<Any>,
    @SerializedName("title")
    val title: String
)