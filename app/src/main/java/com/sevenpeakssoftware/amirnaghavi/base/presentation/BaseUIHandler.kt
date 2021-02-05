package com.sevenpeakssoftware.amirnaghavi.base.presentation

import androidx.viewbinding.ViewBinding
import com.sevenpeakssoftware.amirnaghavi.base.domain.ErrorEntity


interface UIHandler<STATE : BaseState> {
    fun handleState(state: STATE)
    fun startUp()
    fun onResume()
    fun onStart()
    fun onPause()
    fun onStop()
    fun onDestroy()
}

abstract class BaseUIHandler<STATE : ViewModelState,
        VIEW_MODEL : ViewModelContract<STATE>, VIEW_BINDING : ViewBinding>(
        private val viewModel: VIEW_MODEL,
        private val viewBinding: VIEW_BINDING
) : UIHandler<STATE> {

    override fun startUp() {
        startUp(viewBinding, viewModel)
    }

    override fun onResume() {
        onResume(viewBinding, viewModel)
    }

    override fun onStart() {
        onStart(viewBinding, viewModel)

    }

    override fun onPause() {
        onPause(viewBinding, viewModel)

    }

    override fun onStop() {
        onStop(viewBinding, viewModel)

    }

    override fun onDestroy() {
        onDestroy(viewBinding, viewModel)

    }

    open fun onResume(binding: VIEW_BINDING, viewModel: VIEW_MODEL) {}
    open fun onStart(binding: VIEW_BINDING, viewModel: VIEW_MODEL) {}
    open fun onPause(binding: VIEW_BINDING, viewModel: VIEW_MODEL) {}
    open fun onStop(binding: VIEW_BINDING, viewModel: VIEW_MODEL) {}
    open fun onDestroy(binding: VIEW_BINDING, viewModel: VIEW_MODEL) {}
    open fun startUp(binding: VIEW_BINDING, viewModel: VIEW_MODEL) {}

    override fun handleState(state: STATE) {
        handleLoading(state.baseState.loading, viewBinding)
        if (state.baseState.error.isError()) {
            handleError(state.baseState.error, viewBinding)
        }
        bindUiState(state, viewBinding, viewModel)
    }

    abstract fun bindUiState(state: STATE, binding: VIEW_BINDING, viewModel: VIEW_MODEL)

    abstract fun handleError(error: ErrorEntity, binding: VIEW_BINDING)

    abstract fun handleLoading(loading: Boolean, binding: VIEW_BINDING)
}

