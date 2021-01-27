package com.sevenpeakssoftware.amirnaghavi.presentation.event_handler

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.CarEvent
import com.sevenpeakssoftware.amirnaghavi.presentation.CarEventContract
import com.sevenpeakssoftware.amirnaghavi.presentation.CarState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class CarEventHandler(
        compositeDisposable: CompositeDisposable,
        private val useCase: ObservableUseCase<List<CarEntity>, CarsParam>
) : EventHandler<CarEvent, CarState, CarsParam, List<CarEntity>>(compositeDisposable, CarEventContract()) {

    override fun triggerAction(param: CarsParam, initState: CarState): Observable<Answer<List<CarEntity>>> =
            useCase.execute(param, RepositoryStrategy.OfflineFirst).apply {

            }

    override fun onSuccess(answer: Answer<List<CarEntity>>, initState: CarState): CarState =
            initState.copy(
                    data = CarState.Data.Cars(answer.extractData()),
                    baseState = initState.baseState.noErrorNoLoading()
            )

    override fun onFailure(answer: Answer<List<CarEntity>>, initState: CarState): CarState =
            initState.copy(
                    data = CarState.Data.NoData,
                    baseState = initState.baseState.onErrorNoLoading(answer.extractError())
            )

    override fun onIdle(initState: CarState): CarState =
            initState.copy(
                    data = CarState.Data.NoData,
                    baseState = initState.baseState.loading()
            )

}