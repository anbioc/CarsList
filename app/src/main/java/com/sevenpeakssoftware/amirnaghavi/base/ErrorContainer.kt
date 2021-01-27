package com.sevenpeakssoftware.amirnaghavi.base


interface ErrorContainer {
    fun getError(throwable: Throwable): ErrorEntity
}