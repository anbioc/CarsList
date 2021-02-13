package com.multithread.car.base.domain


interface ErrorContainer {
    fun getError(throwable: Throwable): ErrorEntity
}