package com.multithread.car.presentation.car.handler

import com.multithread.car.base.*
import com.multithread.car.base.domain.Answer
import com.multithread.car.base.domain.ObservableUseCase
import com.multithread.car.base.domain.RepositoryStrategy
import com.multithread.car.base.presentation.CompositeEventHandler
import com.multithread.car.base.presentation.EventHandler
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.presentation.car.GetCarInfoEvent
import com.multithread.car.presentation.car.EventContractID
import com.multithread.car.presentation.car.state.CarState
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