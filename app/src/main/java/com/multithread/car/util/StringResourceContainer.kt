package com.multithread.car.util

import android.annotation.SuppressLint
import android.content.Context
import androidx.annotation.IdRes

class StringResourceHolderImpl(private val context: Context) : StringResourceHolder {
    @SuppressLint("ResourceType")
    override fun getString(@IdRes id: Int): String = context.getString(id)
}

interface StringResourceHolder {
    @SuppressLint("ResourceType")
    fun getString(@IdRes id: Int): String
}