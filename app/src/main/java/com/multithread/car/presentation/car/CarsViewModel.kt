package com.multithread.car.presentation.car

import com.multithread.car.base.*
import com.multithread.car.base.presentation.*
import com.multithread.car.presentation.car.state.CarState
import javax.inject.Inject

class CarsViewModel @Inject constructor(
    eventHandlerManager: CompositeEventHandler<CarState, CarsParam>
) : BaseViewModel<CarState, GetCarInfoEvent, CarsParam>(eventHandlerManager) {
    override val initState: CarState = CarState()
}

class GetCarInfoEvent : ViewModelEvent {
    override val ID: String = EventContractID.CAR_EVENT
}
