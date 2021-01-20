package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Observable

interface BaseDataSource<T>

interface Readable<T>: BaseDataSource<T> {
    fun read(): T
}

interface Writeable<T>: BaseDataSource<T> {
    fun write(t: T)
}

abstract class ObservableReadable<T>: Readable<Observable<Answer<T>>>

abstract class ObservableWriteable<T>: Writeable<Observable<Answer<T>>>




