package com.sevenpeakssoftware.amirnaghavi.base


interface BaseEntity

fun<T:BaseEntity > T.toSuccess(): Answer<T> = Answer.Success(this)

