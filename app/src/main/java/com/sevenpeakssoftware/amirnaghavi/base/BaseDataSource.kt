package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Observable

interface BaseDataSource

interface Readable<T, PARAM: Param>: BaseDataSource {
    fun read(param: PARAM): T
}

interface Writeable<T>: BaseDataSource {
}

interface ReadableAndWriteable<T, R, PARAM: Param>: BaseDataSource{
    fun read(param: PARAM): T
    fun write(input: R)
}

abstract class ObservableReadable<T, PARAM: Param>: Readable<Observable<Answer<T>>, PARAM>

abstract class ObservableReadableAndWriteable<T, PARAM: Param>: ReadableAndWriteable<Observable<Answer<T>>, T, PARAM>







