package com.multithread.car.extension

import android.view.View

fun View.show(show: Boolean) = if (show){
    visibility = View.VISIBLE
}else {
    visibility = View.GONE
}
