package com.multithread.car.base

import com.multithread.car.base.domain.Answer
import io.reactivex.Observable
import java.lang.IllegalArgumentException

/*
 * DataSource
 */

/**
 * Base strategy data source, all data sources must implement this or it's sub-classes.
 */
interface BaseStrategyDataSource<INPUT, OUTPUT, PARAM>

/**
 * Readable only base strategy data source, it has only read functionality to differentiate
 * read and write operations.
 */
interface StrategyReadableDataSource<OUTPUT, PARAM> : BaseStrategyDataSource<Nothing, OUTPUT, PARAM> {
    fun read(param: PARAM): OUTPUT
}

/**
 * Writeable only base strategy data source, it has only write functionality to differentiate
 * read and write operations. useful for observer pattern.
 */
interface StrategyWriteableDataSource<INPUT, OUTPUT, PARAM> : BaseStrategyDataSource<INPUT, OUTPUT, PARAM> {
    fun write(data: INPUT, param: PARAM)
}

/**
 * Writeable and Readable base strategy data source, it has both read and write functionality.
 * useful for most of the data related operations.
 */
interface StrategyReadableAndWriteableDataSource<INPUT, OUTPUT, PARAM> : BaseStrategyDataSource<INPUT, OUTPUT, PARAM> {
    fun write(data: INPUT, param: PARAM)
    fun read(param: PARAM): OUTPUT
}



interface StrategyObservableReadableDataSource<OUTPUT, PARAM> : StrategyReadableDataSource< Observable<Answer<OUTPUT>>, PARAM>


/**
 * Observable Writeable and Readable base strategy data source, it has both read and write functionality.
 * wrapped into [Observable] object.
 * useful for most of the data related operations.
 */
interface StrategyObservableReadableAndWriteableDataSource<INPUT, OUTPUT, PARAM>:
        StrategyReadableAndWriteableDataSource<INPUT, Observable<Answer<OUTPUT>>, PARAM>



/**
 * Base plugin repository strategy, defines the base contract of plugin repository feature.
 */
interface PluginRepositoryStrategy<LOCAL_DATA_SOURCE : BaseStrategyDataSource<INPUT, OUTPUT, PARAM>,
        REMOTE_DATA_SOURCE : BaseStrategyDataSource<READABLE_INPUT, OUTPUT, PARAM>, INPUT, READABLE_INPUT, OUTPUT, PARAM> {
    fun invokeStrategy(
            localDataSource: LOCAL_DATA_SOURCE? = null,
            remoteDataSource: REMOTE_DATA_SOURCE? = null,
            param: PARAM
    ): OUTPUT
}

/**
 * Observable base plugin strategy that wraps [RxJava] [Observable] output.
 */
interface ObservablePluginRepositoryStrategy<TYPE, PARAM : Param> : PluginRepositoryStrategy<
        StrategyReadableAndWriteableDataSource<TYPE, Observable<Answer<TYPE>>, PARAM>,
        StrategyReadableDataSource<Observable<Answer<TYPE>>, PARAM>,
        TYPE,
        Nothing,
        Observable<Answer<TYPE>>,
        PARAM>

/**
 * Offline first plugin strategy.
 */
abstract class OfflineFirstObservablePluginRepositoryStrategy<TYPE, PARAM : Param> :
        ObservablePluginRepositoryStrategy<TYPE, PARAM> {
    override fun invokeStrategy(
            localDataSource: StrategyReadableAndWriteableDataSource<TYPE, Observable<Answer<TYPE>>, PARAM>?,
            remoteDataSource: StrategyReadableDataSource<Observable<Answer<TYPE>>, PARAM>?,
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

/**
 * Remote plugin strategy.
 */
abstract class RemoteObservablePluginRepositoryStrategy<TYPE, PARAM : Param> :
        ObservablePluginRepositoryStrategy<TYPE, PARAM> {
    override fun invokeStrategy(
            localDataSource: StrategyReadableAndWriteableDataSource<TYPE, Observable<Answer<TYPE>>, PARAM>?,
            remoteDataSource: StrategyReadableDataSource<Observable<Answer<TYPE>>, PARAM>?,
            param: PARAM
    ): Observable<Answer<TYPE>> {
        if (remoteDataSource == null)
            throw IllegalArgumentException("remote data source must not be null")
        return remoteDataSource.read(param)
    }
}
