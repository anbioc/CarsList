package com.sevenpeakssoftware.amirnaghavi.base

import androidx.viewbinding.ViewBinding


interface UIStateHandler<STATE : ViewModelState> {
    fun handleState(state: STATE)
    fun isResponsibleTo(state: STATE): Boolean
}

abstract class BaseUIStateHandler<STATE : ViewModelState, VIEW_BINDING : ViewBinding>(
        private val viewBinding: VIEW_BINDING
) : UIStateHandler<STATE> {

    abstract val ID: String

    override fun isResponsibleTo(state: STATE): Boolean = ID == state.ID

    override fun handleState(state: STATE) {
        handleLoading(state.baseState.loading)
        if (state.baseState.error.isError()) {
            handleError()
        }
    }

    abstract fun handleError()

    abstract fun handleLoading(loading: Boolean)
}

interface UIStateHandlerManager<STATE : ViewModelState> {
    fun handleState(state: STATE)
}

abstract class BaseUIStateHandlerManager<STATE_HANDLER : UIStateHandler<STATE>, STATE : ViewModelState> :
        UIStateHandlerManager<STATE> {

    abstract val handlerList: List<STATE_HANDLER>

    override fun handleState(state: STATE) = handlerList.forEach {
        if (it.isResponsibleTo(state)) {
            it.handleState(state)
        }
    }
}