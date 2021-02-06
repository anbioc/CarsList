package com.sevenpeakssoftware.amirnaghavi.presentation.car

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.base.presentation.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.car.handler.CarUIHandler
import javax.inject.Inject

class CarsViewModel @Inject constructor(
    eventHandlerManager: CompositeEventHandler<CarState, CarsParam>
) : BaseViewModel<CarState, GetCarInfoEvent, CarsParam>(eventHandlerManager) {
    override val initState: CarState = CarState()
}

data class CarState(
    var data: Data = Data.Idle,
    override var baseState: CoreState = CoreState()
) : ViewModelState() {
    override val ID: String = CarStateContract.CarState
    sealed class Data {
        data class Cars(val cars: List<CarEntity>) : Data()
        object Idle : Data()
        object NoData : Data()
    }

    override fun accept(handler: BaseUIHandler) {
        (handler as UIHandlerContract<CarState>).handleState(this)
    }
}

class GetCarInfoEvent : ViewModelEvent {
    override val ID: String = EventContractID.CAR_EVENT
}

inline fun <reified T> Any?.tryCast(block: T.() -> Unit) {
    if (this is T) {
        block()
    }
}
