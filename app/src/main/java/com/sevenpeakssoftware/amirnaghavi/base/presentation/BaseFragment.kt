package com.sevenpeakssoftware.amirnaghavi.base.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.sevenpeakssoftware.amirnaghavi.extension.observeLiveData
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<BINDING : ViewBinding,
        UI_HANDLER : UIHandler<STATE>,
        VIEW_MODEL : ViewModelContract<STATE>,
        STATE : ViewModelState>: DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var _uiStateHandler: UI_HANDLER? = null
    private val uiStateHandler: UI_HANDLER get() = _uiStateHandler!!

    private var _binding: BINDING? = null
    val binding: BINDING get() = _binding!!

    val viewModel: VIEW_MODEL by lazy {
        viewModelFactory.create(createViewModel())
    }

    val stateHandlerManager: UIEventHandlerManagerContract = CoreUIEventHandlerManager().apply {
        createUIHandlerList().forEach {
            addUIHandler(it)
        }
    }

    abstract fun createUIHandlerList(): List<BaseUIHandler>

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
        _uiStateHandler = createUiStateHandler(binding)
        uiStateHandler.startUp()
        observeLiveData(viewModel.stateLiveData) {
            it.acceptHandlerManager(stateHandlerManager)
        }
    }

    override fun onResume() {
        super.onResume()
        uiStateHandler.onResume()
    }

    override fun onPause() {
        super.onPause()
        uiStateHandler.onPause()
    }

    override fun onStart() {
        super.onStart()
        uiStateHandler.onStart()
    }

    override fun onStop() {
        super.onStop()
        uiStateHandler.onStop()
    }

    override fun onDestroy() {
        uiStateHandler.onDestroy()
        _binding = null
        _uiStateHandler = null
        super.onDestroy()

    }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): BINDING
    abstract fun createUiStateHandler(binding: BINDING): UI_HANDLER
}