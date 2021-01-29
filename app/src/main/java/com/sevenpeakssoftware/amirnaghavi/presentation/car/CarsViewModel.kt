package com.sevenpeakssoftware.amirnaghavi.presentation.car

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.car.event_handler.CarEventHandler
import javax.inject.Inject

class CarsViewModel @Inject constructor(
        private val useCase: ObservableUseCase<List<CarEntity>, CarsParam>
) : BaseViewModel<CarState, GetCarInfoEvent, CarsParam>() {

    override val eventHandlerManager: CompositeEventHandler<CarState, CarsParam> = CarEventHandlerManager()
    override val initState: CarState = CarState()

    init {
        eventHandlerManager.addHandler(CarEventHandler(compositeDisposable, useCase))
    }
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

class CarEventHandlerManager : CompositeEventHandler<CarState, CarsParam>()