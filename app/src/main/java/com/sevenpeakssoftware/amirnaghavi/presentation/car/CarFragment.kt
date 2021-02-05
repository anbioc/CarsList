package com.sevenpeakssoftware.amirnaghavi.presentation.car

import android.view.LayoutInflater
import android.view.ViewGroup
import com.sevenpeakssoftware.amirnaghavi.base.presentation.BaseFragment
import com.sevenpeakssoftware.amirnaghavi.databinding.FragmentCarBinding
import com.sevenpeakssoftware.amirnaghavi.presentation.car.handler.CarUIHandler
import com.sevenpeakssoftware.amirnaghavi.util.StringResourceHolder
import javax.inject.Inject

class CarFragment : BaseFragment<
        FragmentCarBinding,
        CarUIHandler,
        CarsViewModel,
        CarState>() {

    @Inject
    lateinit var stringHolder: StringResourceHolder

    override fun createViewModel(): Class<CarsViewModel> = CarsViewModel::class.java

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarBinding =
            FragmentCarBinding.inflate(inflater, container, false)

    override fun createUiStateHandler(binding: FragmentCarBinding): CarUIHandler =
            CarUIHandler(binding, viewModel,  stringHolder)

}

