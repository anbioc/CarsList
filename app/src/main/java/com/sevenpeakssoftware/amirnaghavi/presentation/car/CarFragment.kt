package com.sevenpeakssoftware.amirnaghavi.presentation.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.sevenpeakssoftware.amirnaghavi.base.BaseFragment
import com.sevenpeakssoftware.amirnaghavi.base.CarsParam
import com.sevenpeakssoftware.amirnaghavi.databinding.FragmentCarBinding
import com.sevenpeakssoftware.amirnaghavi.presentation.car.handler.CarUIHandler

class CarFragment : BaseFragment<
        FragmentCarBinding,
        CarUIHandler,
        CarsViewModel,
        CarState>() {

    override fun createViewModel(): Class<CarsViewModel> = CarsViewModel::class.java

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarBinding =
            FragmentCarBinding.inflate(inflater, container, false)

    override fun createUiStateHandler(binding: FragmentCarBinding): CarUIHandler =
            CarUIHandler(binding, viewModel)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }
}

