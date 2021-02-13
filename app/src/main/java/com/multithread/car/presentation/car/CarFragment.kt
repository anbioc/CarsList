package com.multithread.car.presentation.car

import android.view.LayoutInflater
import android.view.ViewGroup
import com.multithread.car.base.presentation.BaseFragment
import com.multithread.car.base.presentation.BaseUIHandler
import com.multithread.car.databinding.FragmentCarBinding
import com.multithread.car.presentation.car.handler.CarUIHandler
import com.multithread.car.presentation.car.state.CarState
import com.multithread.car.util.StringResourceHolder
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
            CarUIHandler(binding, viewModel, stringHolder)

    override fun createUIHandlerList(): List<BaseUIHandler> = mutableListOf<BaseUIHandler>().apply {
        add(CarUIHandler(exposeBinding(), viewModel, stringHolder))
    }


}

