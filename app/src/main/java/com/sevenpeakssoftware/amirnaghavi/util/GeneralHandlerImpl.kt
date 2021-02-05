package com.sevenpeakssoftware.amirnaghavi.util

import android.util.Log
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.sevenpeakssoftware.amirnaghavi.base.domain.ErrorContainer
import com.sevenpeakssoftware.amirnaghavi.base.domain.ErrorEntity
import java.io.IOException
import java.net.HttpURLConnection
import javax.inject.Inject

class GeneralHandlerImpl @Inject constructor() : ErrorContainer {
    override fun getError(throwable: Throwable): ErrorEntity {
        Log.d(this.javaClass.canonicalName, "error: $throwable")
        throwable.printStackTrace()
        return when (throwable) {
            is IOException -> ErrorEntity.Network
            is HttpException -> {
                when (throwable.code()) {
                    533 -> ErrorEntity.Network

                    HttpURLConnection.HTTP_NOT_FOUND -> ErrorEntity.NotFound

                    HttpURLConnection.HTTP_FORBIDDEN -> ErrorEntity.AccessDenied

                    HttpURLConnection.HTTP_UNAVAILABLE -> ErrorEntity.ServiceUnavailable

                    else -> ErrorEntity.Unknown()
                }
            }
            else -> ErrorEntity.Unknown(throwable.message.toString())
        }
    }
}