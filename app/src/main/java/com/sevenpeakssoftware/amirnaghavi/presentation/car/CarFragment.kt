package com.sevenpeakssoftware.amirnaghavi.presentation.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.sevenpeakssoftware.amirnaghavi.base.BaseFragment
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

    val viewModel by lazy {
        viewModelFactory.create(CarsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentCarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeLiveData(viewModel.stateLiveData){state ->
            showLoading(state.baseState.loading)
            if (state.baseState.error.isError()){
                handleError(state.baseState.error)
            }
            when(state.data) {
                CarState.Data.Idle -> {
                    // do nothing
                }
                CarState.Data.NoData -> {
                    handleNoData();
                }
                is CarState.Data.Cars->{
                    handleData((state.data as CarState.Data.Cars).cars)
                }
            }
        }
    }

    private fun handleData(cars: List<CarEntity>) {
        TODO("Not yet implemented")
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