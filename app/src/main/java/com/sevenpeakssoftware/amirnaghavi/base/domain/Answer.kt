package com.sevenpeakssoftware.amirnaghavi.base.domain

import io.reactivex.Observable


/**
 * Encapsulates domain layer query results.
 */
sealed class Answer<T> {

    data class Success<T>(val data: T) : Answer<T>()
    data class Failure<T> (val error: ErrorEntity) : Answer<T>()
    class Loading<T> : Answer<T>()


    fun isSuccess(): Boolean = this is Success<*>
    fun isFailure(): Boolean = this is Failure
    fun isLoading(): Boolean = this is Loading
    fun isEmpty(): Boolean {
        return if (isSuccess()){
            (this as Success<List<*>>).data.isEmpty()
        }else {
            true
        }
    }

    fun extractError() = with(this){
        if (!isFailure())
            ErrorEntity.NoError
        else {
            (this as Failure<T>).error
        }
    }

    fun extractData(): T = with(this) {
        if (!isSuccess())
            throw IllegalArgumentException("data not found")

        (this as Success<T>).data
    }

}

fun<T> List<T>.toSuccessAnswer() = Answer.Success(this)
fun<T> Answer<T>.toObservable() = Observable.just(this)
