package com.multithread.car.presentation.car.handler

import com.multithread.car.base.domain.ErrorEntity
import com.multithread.car.base.presentation.CoreUIHandler
import com.multithread.car.databinding.FragmentCarBinding
import com.multithread.car.presentation.car.CarsViewModel
import com.multithread.car.presentation.car.state.CarState
import com.multithread.car.util.StringResourceHolder

class CarFooterUIHandler(binding: FragmentCarBinding,
                         viewModel: CarsViewModel,
                         private val stringResourceHolder: StringResourceHolder):
        CoreUIHandler<CarState, CarsViewModel, FragmentCarBinding>(viewModel, binding) {

    override val ID: String
        get() = TODO("Not yet implemented")

    override fun bindUiState(state: CarState, binding: FragmentCarBinding, viewModel: CarsViewModel) {
        TODO("Not yet implemented")
    }

    override fun handleError(error: ErrorEntity, binding: FragmentCarBinding) {
        TODO("Not yet implemented")
    }

    override fun handleLoading(loading: Boolean, binding: FragmentCarBinding) {
        TODO("Not yet implemented")
    }

}