package com.sevenpeakssoftware.amirnaghavi.presentation.car.handler

import com.sevenpeakssoftware.amirnaghavi.base.domain.ErrorEntity
import com.sevenpeakssoftware.amirnaghavi.base.presentation.CoreUIHandler
import com.sevenpeakssoftware.amirnaghavi.databinding.FragmentCarBinding
import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarsViewModel
import com.sevenpeakssoftware.amirnaghavi.presentation.car.state.CarState
import com.sevenpeakssoftware.amirnaghavi.util.StringResourceHolder

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