package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Observable

interface BaseRepository<T, PARAM: Param> {
    fun getResult(param: PARAM, strategy: RepositoryStrategy): T
}

abstract class ObservableRepository<T, PARAM: Param> : BaseRepository<Observable<Answer<T>>,PARAM> {

    protected abstract fun getOffline(param: PARAM): Observable<Answer<T>>
    protected abstract fun getRemote(param: PARAM): Observable<Answer<T>>

    private fun getOfflineFirst(param: PARAM) = Observable.create<Answer<T>> {
        it.onNext(getOffline(param).blockingFirst())
        it.onNext(getRemote(param).blockingFirst())
    }
//        Observable.concatArrayEagerDelayError(
//            getOffline(param), getRemote(param)
//        )

    override fun getResult(param: PARAM, strategy: RepositoryStrategy): Observable<Answer<T>> =
        when (strategy) {
            RepositoryStrategy.OfflineFirst -> getOfflineFirst(param)
            RepositoryStrategy.Remote -> getRemote(param)
        }

}