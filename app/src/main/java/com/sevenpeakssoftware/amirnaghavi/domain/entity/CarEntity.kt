package com.sevenpeakssoftware.amirnaghavi.domain.entity


data class CarEntity(
    val changed: Int,
    val content: List<CarContentEntity>,
    val created: Int,
    val dateTime: String,
    val id: Int,
    val image: String,
    val ingress: String,
    val tags: List<String>,
    val title: String
)

data class CarContentEntity(
    val description: String,
    val subject: String,
    val type: String
)
