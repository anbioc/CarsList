package com.sevenpeakssoftware.amirnaghavi.presentation

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.event_contract.EventContractID
import com.sevenpeakssoftware.amirnaghavi.presentation.event_handler.CarEventHandler
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
        var data: Data = CarState.Data.Idle,
        override var baseState: BaseState = BaseState()
) : ViewModelState() {
    sealed class Data {
        data class Cars(val car: List<CarEntity>) : Data()
        object Idle : Data()
        object NoData : Data()
    }
}

class GetCarInfoEvent : ViewModelEvent{
    override val ID: String = EventContractID.CAR_EVENT
}

class CarEventHandlerManager : CompositeEventHandler<CarState, CarsParam>()