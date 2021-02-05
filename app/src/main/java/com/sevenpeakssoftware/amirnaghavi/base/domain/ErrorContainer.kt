package com.sevenpeakssoftware.amirnaghavi.base.domain


interface ErrorContainer {
    fun getError(throwable: Throwable): ErrorEntity
}