package com.sevenpeakssoftware.amirnaghavi.base

import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable


/**
 * Hosts the logic to handle incoming event either a network call or db transaction or any other other logic.
 */
interface BaseEventHandler<STATE : ViewModelState, PARAM : Param> {
    fun isResponsibleTo(event: Any): Boolean
    fun handleEvent(param: PARAM, liveData: MutableLiveData<STATE>, initState: STATE)
    val ID: String
}

abstract class EventHandler<EVENT : ViewModelEvent, STATE : ViewModelState, PARAM : Param, RESULT>(
        private val compositeDisposable: CompositeDisposable) :
        BaseEventHandler<STATE, PARAM> {
    /**
     * The core logic will be implemented in this function.
     */
    abstract fun triggerAction(param: PARAM, initState: STATE): Observable<Answer<RESULT>>

    /**
     * Checks weather current [EventHandler] is responsible to handle this event [ViewModelEvent] or not.
     */
    override fun isResponsibleTo(event: Any): Boolean = (ID == (event as ViewModelEvent).ID)

    /**
     * Wraps all necessary steps to handle the input event.
     */
    override fun handleEvent(param: PARAM, liveData: MutableLiveData<STATE>, initState: STATE) {
        onIdle(initState)
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

    /**
     * Prepares the [ViewModelState] when result is success.
     */
    protected abstract fun onSuccess(answer: Answer<RESULT>, initState: STATE): STATE
    /**
     * Prepares the [ViewModelState] when result is failure.
     */
    protected abstract fun onFailure(answer: Answer<RESULT>, initState: STATE): STATE
    /**
     * Prepares the [ViewModelState] when there is no result or some unexpected situation.
     */
    protected abstract fun onIdle(initState: STATE): STATE
}

/**
 * Hosts [EventHandler] objects and manages the overall process of event handling.
 */
interface BaseCompositeEventHandler<STATE : ViewModelState, PARAM : Param> {
    fun handleEvent(event: Any, liveData: MutableLiveData<STATE>, param: PARAM, initState: STATE)
    fun addHandler(handler: BaseEventHandler<STATE, PARAM>)
}

abstract class CompositeEventHandler<STATE : ViewModelState, PARAM : Param> :
        BaseCompositeEventHandler<STATE, PARAM> {

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
