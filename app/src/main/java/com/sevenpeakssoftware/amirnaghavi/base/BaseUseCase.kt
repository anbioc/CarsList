package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Observable
import java.util.concurrent.TimeUnit

interface BaseUseCase<T, PARAM : Param> {
    fun execute(param: PARAM, strategy: RepositoryStrategy): T
}

abstract class ObservableUseCase<T, PARAM : Param>(
    private val scheduler: SchedulerProvider,
    private val errorContainer: ErrorContainer
) : BaseUseCase<Observable<Answer<T>>, PARAM> {

    /**
     * Hosts the main logic of the use-case, return result wrapped around [Observable] object.
     */
    protected abstract fun buildObservable(
        params: PARAM,
        strategy: RepositoryStrategy
    ): Observable<Answer<T>>

    override fun execute(param: PARAM, strategy: RepositoryStrategy): Observable<Answer<T>> =
        buildObservable(param, strategy)
            .onErrorReturn {
                Answer.Failure(errorContainer.getError(it))
            }.doOnError {
                it.printStackTrace()
            }
            .debounce(350, TimeUnit.MICROSECONDS)
            .subscribeOn(scheduler.ioScheduler)
            .observeOn(scheduler.mainScheduler)
}