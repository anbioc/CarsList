package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Observable

interface BaseRepository<T, PARAM : Param> {
    fun getResult(param: PARAM, strategy: RepositoryStrategy): T
}

abstract class ObservableRepository<T, PARAM : Param> :
    BaseRepository<Observable<Answer<T>>, PARAM> {

    protected abstract fun getOffline(param: PARAM): Observable<Answer<T>>
    protected abstract fun getRemote(param: PARAM): Observable<Answer<T>>

    private fun getOfflineFirst(param: PARAM) =
        Observable.concatArrayEagerDelayError(
            triggerOfflineCall(param), triggerRemoteCall(param)
        )

    override fun getResult(param: PARAM, strategy: RepositoryStrategy): Observable<Answer<T>> =
        when (strategy) {
            RepositoryStrategy.OfflineFirst -> getOfflineFirst(param)
            RepositoryStrategy.Remote -> triggerRemoteCall(param)
        }

    private fun triggerOfflineCall(param: PARAM): Observable<Answer<T>> =
        getOffline(param).onErrorReturn {
            it.printStackTrace()
            Answer.Failure(ErrorEntity.Unknown(it.localizedMessage))
        }

    private fun triggerRemoteCall(param: PARAM): Observable<Answer<T>> =
        getRemote(param).onErrorReturn {
            it.printStackTrace()
            Answer.Failure(ErrorEntity.Unknown(it.localizedMessage))
        }
}