package com.sevenpeakssoftware.amirnaghavi.presentation

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.event_handler.CarEventHandler
import javax.inject.Inject

class CarsViewModel @Inject constructor(
        private val useCase: ObservableUseCase<List<CarEntity>, CarsParam>
) : BaseViewModel<CarState, CarEvent, CarsParam>() {

    override val eventHandlerManager: EventHandlerManager<CarState, CarsParam> = CarEventHandlerManager()

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

class CarEventContract : EventContract {
    override val ID: String = javaClass.canonicalName
}

class CarEvent : ViewModelEvent(CarEventContract()) {
    override val ID: String = javaClass.canonicalName ?: "CarEvent"
}

class CarEventHandlerManager : EventHandlerManager<CarState, CarsParam>()