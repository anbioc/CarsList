package com.sevenpeakssoftware.amirnaghavi.base

import androidx.lifecycle.MutableLiveData
import com.sevenpeakssoftware.amirnaghavi.presentation.CarState
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable


interface BaseEventHandler<STATE : ViewModelState, PARAM : Param> {
    fun isResponsibleTo(event: Any): Boolean
    fun handleEvent(param: PARAM, liveData: MutableLiveData<STATE>, initState: STATE)
}

abstract class EventHandler<EVENT : BaseViewModelEvent, STATE : ViewModelState, PARAM : Param, RESULT>(
        private val compositeDisposable: CompositeDisposable, contract: EventContract) :
        BaseEventHandler<STATE, PARAM> {
    val ID: String = contract.ID
    abstract fun triggerAction(param: PARAM, initState: STATE): Observable<Answer<RESULT>>
    override fun isResponsibleTo(event: Any): Boolean = (ID == (event as BaseViewModelEvent).ID)
    override fun handleEvent(param: PARAM, liveData: MutableLiveData<STATE>, initState: STATE) {
        compositeDisposable.add(triggerAction(param, initState).subscribe({
            when {
                it.isSuccess() -> {
                    liveData.postValue(onSuccess(it, initState))
                }
                it.isFailure() -> {
                    liveData.postValue(onFailure(it, initState))
                }
                else -> {
                    liveData.postValue(onIdle(initState))
                }
            }
        }, {

        }))
    }

    protected abstract fun onSuccess(answer: Answer<RESULT>, initState: STATE): STATE
    protected abstract fun onFailure(answer: Answer<RESULT>, initState: STATE): STATE
    protected abstract fun onIdle(initState: STATE): STATE
}


interface BaseEventHandlerManager<STATE : ViewModelState, PARAM : Param> {
    fun handleEvent(event: Any, liveData: MutableLiveData<STATE>, param: PARAM, initState: STATE)
    fun addHandler(handler: BaseEventHandler<STATE, PARAM>)
}

abstract class EventHandlerManager<STATE : ViewModelState, PARAM : Param> :
        BaseEventHandlerManager<STATE, PARAM> {

    private val handlers: MutableList<BaseEventHandler<STATE, PARAM>> = mutableListOf()
    override fun addHandler(handler: BaseEventHandler<STATE, PARAM>) {
        handlers.add(handler)
    }

    override fun handleEvent(event: Any, liveData: MutableLiveData<STATE>, param: PARAM, initState: STATE) {
        handlers.forEach { handler ->
            if (handler.isResponsibleTo(event)) {
                handler.handleEvent(param, liveData, initState)
            }
        }
    }
}
