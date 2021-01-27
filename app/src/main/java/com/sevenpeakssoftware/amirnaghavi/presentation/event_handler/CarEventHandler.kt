package com.sevenpeakssoftware.amirnaghavi.presentation.event_handler

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.GetCarInfoEvent
import com.sevenpeakssoftware.amirnaghavi.presentation.CarState
import com.sevenpeakssoftware.amirnaghavi.presentation.event_contract.EventContractID
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable

class CarEventHandler(
        compositeDisposable: CompositeDisposable,
        private val useCase: ObservableUseCase<List<CarEntity>, CarsParam>
) : EventHandler<GetCarInfoEvent, CarState, CarsParam, List<CarEntity>>(compositeDisposable) {

    override val ID: String = EventContractID.CAR_EVENT

    override fun triggerAction(param: CarsParam, initState: CarState): Observable<Answer<List<CarEntity>>> {
        return useCase.execute(param, RepositoryStrategy.OfflineFirst)
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