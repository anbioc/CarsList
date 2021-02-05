package com.sevenpeakssoftware.amirnaghavi.base

import androidx.viewbinding.ViewBinding


interface UIStateHandler<STATE : BaseState> {
    fun handleState(state: STATE)
}

abstract class BaseUIStateHandler<STATE : ViewModelState,
        VIEW_MODEL : ViewModelContract<STATE>, VIEW_BINDING : ViewBinding>(
        private val viewModel: VIEW_MODEL,
        private val viewBinding: VIEW_BINDING
) : UIStateHandler<STATE> {

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

