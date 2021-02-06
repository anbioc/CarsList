package com.sevenpeakssoftware.amirnaghavi.presentation.car.state

import com.sevenpeakssoftware.amirnaghavi.base.presentation.BaseUIHandler
import com.sevenpeakssoftware.amirnaghavi.base.presentation.CoreState
import com.sevenpeakssoftware.amirnaghavi.base.presentation.UIHandlerContract
import com.sevenpeakssoftware.amirnaghavi.base.presentation.ViewModelState
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarStateContract

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
