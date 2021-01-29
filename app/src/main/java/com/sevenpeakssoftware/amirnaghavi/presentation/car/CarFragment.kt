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
import javax.inject.Inject

class CarFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    private var _binding: FragmentCarBinding? = null
    private val binding: FragmentCarBinding get() = _binding!!

    private val viewModel by lazy {
        viewModelFactory.create(CarsViewModel::class.java)
    }

    private val carListAdapter by lazy {
        CarsListAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        observeData()
        initListeners()
        viewModel.handleEvent(GetCarInfoEvent(), CarsParam())
    }

    private fun initListeners() {
        binding.noDataContainer.noDataTryAgainButton.setOnClickListener {
            viewModel.handleEvent(GetCarInfoEvent(), CarsParam())
        }
    }

    private fun setupRecyclerView() {
        binding.carList.adapter = carListAdapter
    }

    private fun observeData() {
        observeLiveData(viewModel.stateLiveData) { state ->
            showLoading(state.baseState.loading)
            if (state.baseState.error.isError()) {
                handleError(state.baseState.error)
            }
            when (state.data) {
                CarState.Data.Idle -> {
                    // do nothing
                }
                CarState.Data.NoData -> {
                    handleNoData();
                }
                is CarState.Data.Cars -> {
                    handleData((state.data as CarState.Data.Cars).cars)
                }
            }
        }
    }

    private fun handleData(cars: List<CarEntity>) {
        carListAdapter.itemList = cars
    }

    private fun handleNoData() {
        TODO("Not yet implemented")
    }

    private fun handleError(error: ErrorEntity) {
        TODO("Not yet implemented")
    }

    private fun showLoading(loading: Boolean) {
        TODO("Not yet implemented")
    }


}