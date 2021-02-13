package com.multithread.car.base

interface HandlerContract {
    val ID: String
    fun handle(input: Any)
}