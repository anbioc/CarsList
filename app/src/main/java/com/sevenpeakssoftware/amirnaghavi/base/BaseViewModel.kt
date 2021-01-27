package com.sevenpeakssoftware.amirnaghavi.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject

abstract class BaseViewModel<STATE : ViewModelState, EVENT : ViewModelEvent, PARAM : Param> : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()
    protected abstract val eventHandlerManager: EventHandlerManager<STATE, PARAM>

    private val _stateLiveData: MutableLiveData<STATE> = MutableLiveData()
    val stateLiveData: LiveData<STATE> get() = _stateLiveData

    /**
     * Notifies [EventHandler] about triggered events.
     */
    private val eventBridge: PublishSubject<Pair<EVENT, PARAM>> = PublishSubject.create()

    init {
        eventBridge.map {
            eventHandlerManager.handleEvent(it.first, _stateLiveData, it.second)
        }.subscribe()
    }


    /**
     * Call this method to add appropriate event handlers to the handlers list.
     */
    protected fun addHandler(handler: BaseEventHandler<STATE, PARAM>){
        eventHandlerManager.addHandler(handler)
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

interface EventContract {
    val ID: String
}

interface BaseViewModelEvent {
    val ID: String
}

abstract class ViewModelEvent(contact: EventContract) : BaseViewModelEvent {
    override val ID: String = contact.ID
}


