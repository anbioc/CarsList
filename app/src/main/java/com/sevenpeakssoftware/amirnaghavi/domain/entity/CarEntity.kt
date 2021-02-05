package com.sevenpeakssoftware.amirnaghavi.domain.entity

import com.sevenpeakssoftware.amirnaghavi.base.domain.BaseEntity


data class CarEntity(
    val changed: Int,
    val content: List<CarContentEntity>,
    val created: Int,
    val dateTime: String,
    val id: Int,
    val image: String,
    val ingress: String,
    val title: String
): BaseEntity

data class CarContentEntity(
    val description: String,
    val subject: String,
    val type: String
)
