package com.sevenpeakssoftware.amirnaghavi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

/**
 * Base [ViewModel] class responsible to process UI event emissions using State pattern.
 */
abstract class BaseViewModel<STATE : ViewModelState, EVENT : ViewModelEvent, PARAM : Param> : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    protected abstract val eventHandlerManager: CompositeEventHandler<STATE, PARAM>

    /**
     * Initial state.
     */
    abstract val initState: STATE

    /**
     * The exit point of the process, each operation would be summed up at this point, resulting to a [ViewModelState]
     */
    private val _stateLiveData: MutableLiveData<STATE> = MutableLiveData()
    val stateLiveData: LiveData<STATE> get() = _stateLiveData

    /**
     * The starting point of the operation, each [ViewModelEvent] would start it's journey from this point and
     * [EventHandler]s would be notified for each event emission.
     */
    private val eventBridge: PublishSubject<Pair<EVENT, PARAM>> = PublishSubject.create()

    init {
        /**
         * Wires up [ViewModelEvent] emissions to the [CompositeEventHandler] as the event handler manager.
         */
        eventBridge.map {
            eventHandlerManager.handleEvent(it.first, _stateLiveData, it.second, initState)
        }.subscribe()
    }

    /**
     * Handles incoming events from UI.
     */
    fun handleEvent(event: EVENT, param: PARAM) = eventBridge.onNext(Pair(event, param))


    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}

abstract class ViewModelState {
    open var baseState: BaseState = BaseState()
}

interface ViewModelEvent {
    val ID: String
}



