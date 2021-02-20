package com.multithread.car.base

import com.multithread.car.base.domain.Answer
import io.reactivex.Observable
import java.lang.IllegalArgumentException

/**
 * DataSource
 */
interface BaseStrategyDataSource<INPUT, OUTPUT, PARAM>

interface StrategyReadable<OUTPUT, PARAM> : BaseStrategyDataSource<Nothing, OUTPUT, PARAM> {
    fun read(param: PARAM): OUTPUT
}

interface StrategyWriteable<INPUT, OUTPUT, PARAM> : BaseStrategyDataSource<INPUT, OUTPUT, PARAM> {
    fun write(data: INPUT, param: PARAM)
}

interface StrategyReadableAndWriteable<INPUT, OUTPUT, PARAM> : BaseStrategyDataSource<INPUT, OUTPUT, PARAM> {
    fun write(data: INPUT, param: PARAM)
    fun read(param: PARAM): OUTPUT
}


/**
 * Repository Strategy
 */
interface PluginRepositoryStrategy<LOCAL_DATA_SOURCE : BaseStrategyDataSource<INPUT, OUTPUT, PARAM>,
        REMOTE_DATA_SOURCE : BaseStrategyDataSource<READABLE_INPUT, OUTPUT, PARAM>, INPUT, READABLE_INPUT, OUTPUT, PARAM> {
    fun invokeStrategy(
            localDataSource: LOCAL_DATA_SOURCE? = null,
            remoteDataSource: REMOTE_DATA_SOURCE? = null,
            param: PARAM
    ): OUTPUT
}

interface ObservablePluginRepositoryStrategy<TYPE, PARAM : Param> : PluginRepositoryStrategy<
        StrategyReadableAndWriteable<TYPE, Observable<Answer<TYPE>>, PARAM>,
        StrategyReadable<Observable<Answer<TYPE>>, PARAM>,
        TYPE,
        Nothing,
        Observable<Answer<TYPE>>,
        PARAM>

abstract class OfflineFirstObservablePluginRepositoryStrategy<TYPE, PARAM : Param> :
        ObservablePluginRepositoryStrategy<TYPE, PARAM> {
    override fun invokeStrategy(
            localDataSource: StrategyReadableAndWriteable<TYPE, Observable<Answer<TYPE>>, PARAM>?,
            remoteDataSource: StrategyReadable<Observable<Answer<TYPE>>, PARAM>?,
            param: PARAM
    ): Observable<Answer<TYPE>> {
        when {
            (localDataSource == null) -> {
                throw IllegalArgumentException("local data source must not be null")
            }
            (remoteDataSource == null) -> {
                throw IllegalArgumentException("remote data source must not be null")
            }
            else -> {
                return Observable.concatArrayEagerDelayError(
                        localDataSource.read(param),
                        remoteDataSource.read(param).doOnNext {
                            if (it.isSuccess()) {
                                localDataSource.write(it.extractData(), param)
                            }
                        }
                )
            }
        }
    }
}


abstract class RemoteObservablePluginRepositoryStrategy<TYPE, PARAM : Param> :
        ObservablePluginRepositoryStrategy<TYPE, PARAM> {
    override fun invokeStrategy(
            localDataSource: StrategyReadableAndWriteable<TYPE, Observable<Answer<TYPE>>, PARAM>?,
            remoteDataSource: StrategyReadable<Observable<Answer<TYPE>>, PARAM>?,
            param: PARAM
    ): Observable<Answer<TYPE>> {
        if (remoteDataSource == null)
            throw IllegalArgumentException("remote data source must not be null")
        return remoteDataSource.read(param)
    }
}
