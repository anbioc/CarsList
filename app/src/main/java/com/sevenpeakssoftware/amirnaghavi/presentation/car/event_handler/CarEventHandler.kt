package com.sevenpeakssoftware.amirnaghavi.presentation.car.event_handler

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.car.GetCarInfoEvent
import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarState
import com.sevenpeakssoftware.amirnaghavi.presentation.car.EventContractID
import io.reactivex.Observable
import javax.inject.Inject

class CarEventHandler(
        private val useCase: ObservableUseCase<List<CarEntity>, CarsParam>,
        schedulerProvider: SchedulerProvider,
) : EventHandler<GetCarInfoEvent, CarState, CarsParam, List<CarEntity>>(
        schedulerProvider
) {

    override val ID: String = EventContractID.CAR_EVENT

    override fun triggerAction(
            param: CarsParam,
            initState: CarState
    ): Observable<Answer<List<CarEntity>>> {
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
                    data = CarState.Data.Idle,
                    baseState = initState.baseState.loading()
            )

}


class CarEventHandlerManager @Inject constructor(): CompositeEventHandler<CarState, CarsParam>()