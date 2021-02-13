package com.multithread.car.presentation.car.state

import com.multithread.car.base.presentation.BaseUIHandler
import com.multithread.car.base.presentation.CoreState
import com.multithread.car.base.presentation.UIHandlerContract
import com.multithread.car.base.presentation.ViewModelState
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.presentation.car.CarStateContract

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
