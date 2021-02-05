package com.sevenpeakssoftware.amirnaghavi.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.sevenpeakssoftware.amirnaghavi.databinding.FragmentCarBinding
import com.sevenpeakssoftware.amirnaghavi.extension.observeLiveData
import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarsViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<BINDING : ViewBinding,
        UI_STATE : UIStateHandler<STATE>,
        VIEW_MODEL : ViewModelContract<STATE>,
        STATE : ViewModelState> : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    var uiStateHandler: UI_STATE? = null

    private var _binding: BINDING? = null
    protected val binding: BINDING get() = _binding!!

    val viewModel: VIEW_MODEL by lazy {
        viewModelFactory.create(createViewModel())
    }

    abstract fun createViewModel(): Class<VIEW_MODEL>

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = createBinding(inflater, container)
        return _binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        uiStateHandler = createUiStateHandler(binding)
        observeLiveData(viewModel.stateLiveData) {
            uiStateHandler!!.handleState(it)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
        uiStateHandler = null
    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING
    abstract fun createUiStateHandler(binding: BINDING): UI_STATE
}