package com.sevenpeakssoftware.amirnaghavi.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer


inline fun <T> Fragment.observeLiveData(
    liveData: LiveData<T>, crossinline onChanged: (data: T) -> Unit
) {
    liveData.observe(requireActivity(), {
        onChanged(it)
    })
}