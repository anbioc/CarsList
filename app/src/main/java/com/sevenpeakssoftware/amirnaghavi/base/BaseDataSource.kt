package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Observable

interface BaseDataSource

interface Readable<T>: BaseDataSource {
    fun read(): T
}

interface Writeable<T>: BaseDataSource {
    fun write(t: T): Comparable<Boolean>
}

interface ReadableAndWriteable<T, R>: BaseDataSource{
    fun read(): T
    fun write(input: R)
}

abstract class ObservableReadable<T>: Readable<Observable<Answer<T>>>

abstract class ObservableWriteable<T>: Writeable<T>

abstract class ObservableReadableAndWriteable<T>: ReadableAndWriteable<Observable<Answer<T>>, T>







