package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Observable
import java.lang.IllegalStateException


/**
 * Hosts the logic to handle incoming events, could be any type of request to domain layer.
 */
interface BaseEventHandler<STATE : ViewModelState, PARAM : Param> {

    fun isResponsibleTo(event: Any): Boolean

    fun handleEvent(param: PARAM, initState: STATE): Observable<STATE>
    val ID: String
}

abstract class EventHandler<EVENT : ViewModelEvent, STATE : ViewModelState, PARAM : Param, RESULT>(
        private val schedulerProvider: SchedulerProvider
) : BaseEventHandler<STATE, PARAM> {
    /**
     * The core logic will be implemented in this function.
     */
    abstract fun triggerAction(param: PARAM, initState: STATE): Observable<Answer<RESULT>>

    /**
     * Checks weather current [EventHandler] is responsible to handle this [ViewModelEvent] or not.
     */
    override fun isResponsibleTo(event: Any): Boolean = (ID == (event as ViewModelEvent).ID)

    /**
     * Wraps all necessary steps to handle the input event, reduces the domain [Answer] to the final UI state.
     */
    override fun handleEvent(param: PARAM, initState: STATE): Observable<STATE> =
            triggerAction(param, initState)
                    .startWith(Answer.Loading())
                    .map {
                        when {
                            it.isSuccess() -> {
                                onSuccess(it, initState)
                            }
                            it.isFailure() -> {
                                onFailure(it, initState)
                            }
                            else -> {
                                onIdle(initState)
                            }
                        }
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
 * Contains a list of [EventHandler] objects and manages the overall process of event handling.
 */
interface BaseCompositeEventHandler<STATE : ViewModelState, PARAM : Param> {
    fun handleEvent(event: Any, param: PARAM, initState: STATE): Observable<STATE>
    fun addHandler(handler: BaseEventHandler<STATE, PARAM>)
}

/**
 * Holds event handlers list and decide which one is responsible to handle current event.
 */
abstract class CompositeEventHandler<STATE : ViewModelState, PARAM : Param> :
        BaseCompositeEventHandler<STATE, PARAM> {

    private val handlers: MutableList<BaseEventHandler<STATE, PARAM>> = mutableListOf()
    override fun addHandler(handler: BaseEventHandler<STATE, PARAM>) {
        handlers.add(handler)
    }

    override fun handleEvent(
            event: Any,
            param: PARAM,
            initState: STATE
    ): Observable<STATE> {
        handlers.forEach { handler ->
            if (handler.isResponsibleTo(event)) {
                return handler.handleEvent(param, initState)
            }
        }
        throw IllegalStateException("handler not found")
    }
}
