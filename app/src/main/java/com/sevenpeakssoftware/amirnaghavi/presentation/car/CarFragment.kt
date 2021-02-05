package com.sevenpeakssoftware.amirnaghavi.presentation.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sevenpeakssoftware.amirnaghavi.base.BaseFragment
import com.sevenpeakssoftware.amirnaghavi.base.CarsParam
import com.sevenpeakssoftware.amirnaghavi.base.ErrorEntity
import com.sevenpeakssoftware.amirnaghavi.databinding.FragmentCarBinding
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.extension.observeLiveData
import com.sevenpeakssoftware.amirnaghavi.extension.show
import com.sevenpeakssoftware.amirnaghavi.presentation.car.handler.CarUIStateHandler
import javax.inject.Inject

class CarFragment : BaseFragment<
        FragmentCarBinding,
        CarUIStateHandler,
        CarsViewModel,
        CarState>() {

    override fun createViewModel(): Class<CarsViewModel> = CarsViewModel::class.java

    override fun createBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentCarBinding =
            FragmentCarBinding.inflate(inflater, container, false)

    override fun createUiStateHandler(binding: FragmentCarBinding): CarUIStateHandler =
            CarUIStateHandler(binding, viewModel)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.handleEvent(GetCarInfoEvent(), CarsParam())
    }
}

