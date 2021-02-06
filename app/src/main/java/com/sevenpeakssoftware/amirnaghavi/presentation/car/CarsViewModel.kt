package com.sevenpeakssoftware.amirnaghavi.presentation.car

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.base.presentation.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.car.handler.CarUIHandler
import com.sevenpeakssoftware.amirnaghavi.presentation.car.state.CarState
import javax.inject.Inject

class CarsViewModel @Inject constructor(
    eventHandlerManager: CompositeEventHandler<CarState, CarsParam>
) : BaseViewModel<CarState, GetCarInfoEvent, CarsParam>(eventHandlerManager) {
    override val initState: CarState = CarState()
}

class GetCarInfoEvent : ViewModelEvent {
    override val ID: String = EventContractID.CAR_EVENT
}
