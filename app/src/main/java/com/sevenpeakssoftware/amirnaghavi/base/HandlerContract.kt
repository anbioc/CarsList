package com.sevenpeakssoftware.amirnaghavi.base

interface HandlerContract {
    val ID: String
    fun handle(input: Any)
}