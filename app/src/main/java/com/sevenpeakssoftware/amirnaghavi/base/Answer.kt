package com.sevenpeakssoftware.amirnaghavi.base

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

}

fun<T> List<T>.toSuccessAnswer() = Answer.Success(this)
fun<T> Answer<T>.toObservable() = Observable.just(this)
