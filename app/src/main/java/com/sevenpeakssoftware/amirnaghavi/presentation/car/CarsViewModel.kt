package com.sevenpeakssoftware.amirnaghavi.presentation.car

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.car.event_handler.CarEventHandler
import javax.inject.Inject

class CarsViewModel @Inject constructor(
    eventHandlerManager: CompositeEventHandler<CarState, CarsParam>
) : BaseViewModel<CarState, GetCarInfoEvent, CarsParam>(eventHandlerManager) {

    override val initState: CarState = CarState()

}

data class CarState(
    var data: Data = Data.Idle,
    override var baseState: BaseState = BaseState()
) : ViewModelState() {
    sealed class Data {
        data class Cars(val cars: List<CarEntity>) : Data()
        object Idle : Data()
        object NoData : Data()
    }
}

class GetCarInfoEvent : ViewModelEvent{
    override val ID: String = EventContractID.CAR_EVENT
}

