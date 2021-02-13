package com.multithread.car.base.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer

/**
 * Tracks and records emitted data from live data to further analysis.
 */
class HistoryObserver<T> : Observer<T>, LifecycleOwner {
    private val lifecycle = LifecycleRegistry(this)
    private val history: MutableList<T> = mutableListOf()

    init {
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    override fun getLifecycle(): Lifecycle = lifecycle

    override fun onChanged(t: T) {
        history.clear()
        history.add(t)
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    }

    fun getHistoryItem() = if (history.isNotEmpty()) {
        history[0]
    } else {
        throw IllegalArgumentException("History is empty")
    }

    fun assertNotEmpty() : HistoryObserver<T> {
        if (history.isEmpty()){
            throw IllegalStateException()
        }
        return this
    }

}
