package com.multithread.car.presentation.car.handler

import com.multithread.car.base.presentation.CoreUIHandler
import com.multithread.car.base.CarsParam
import com.multithread.car.base.domain.ErrorEntity
import com.multithread.car.databinding.FragmentCarBinding
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.extension.show
import com.multithread.car.presentation.car.*
import com.multithread.car.presentation.car.state.CarState
import com.multithread.car.util.StringResourceHolder

class CarUIHandler(binding: FragmentCarBinding,
                   viewModel: CarsViewModel,
                   private val stringResourceHolder: StringResourceHolder) :
        CoreUIHandler<CarState, CarsViewModel, FragmentCarBinding>(viewModel, binding) {

    override val ID: String = CarStateContract.CarState
    private val carListAdapter by lazy {
        CarsListAdapter()
    }

    override fun startUp(binding: FragmentCarBinding, viewModel: CarsViewModel) {
        viewModel.handleEvent(GetCarInfoEvent(), CarsParam())
    }

    override fun bindUiState(state: CarState, binding: FragmentCarBinding, viewModel: CarsViewModel) {
        setupRecyclerView(binding)
        initListeners(binding, viewModel)
        when (state.data) {
            CarState.Data.Idle -> {
                // do nothing
            }
            CarState.Data.NoData -> {
                handleNoData(binding)
            }
            is CarState.Data.Cars -> {
                handleData((state.data as CarState.Data.Cars).cars, binding)
            }
        }
    }

    private fun handleData(cars: List<CarEntity>, binding: FragmentCarBinding) {
        binding.noDataContainer.noDataContainer.show(false)
        binding.carListContainer.show(true)
        carListAdapter.itemList = cars
    }

    private fun handleNoData(binding: FragmentCarBinding) {
        binding.noDataContainer.root.show(true)
        binding.carListContainer.show(false)
    }

    private fun initListeners(binding: FragmentCarBinding, viewModel: CarsViewModel) {
        binding.noDataContainer.noDataTryAgainButton.setOnClickListener {
            viewModel.handleEvent(GetCarInfoEvent(), CarsParam())
        }
    }

    private fun setupRecyclerView(binding: FragmentCarBinding) {
        binding.carList.adapter = carListAdapter
    }

    override fun handleError(error: ErrorEntity, binding: FragmentCarBinding) {
        binding.noDataContainer.root.show(true)
        binding.carListContainer.show(false)
    }

    override fun handleLoading(loading: Boolean, binding: FragmentCarBinding) {
        binding.carLoader.show(loading)
    }

    override fun onResume(binding: FragmentCarBinding, viewModel: CarsViewModel) {
        // do nothing
    }

    override fun onStart(binding: FragmentCarBinding, viewModel: CarsViewModel) {
        // do nothing
    }

    override fun onPause(binding: FragmentCarBinding, viewModel: CarsViewModel) {
        // do nothing
    }

    override fun onStop(binding: FragmentCarBinding, viewModel: CarsViewModel) {
        // do nothing
    }

    override fun onDestroy(binding: FragmentCarBinding, viewModel: CarsViewModel) {
        // do nothing
    }
}


