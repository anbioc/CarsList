package com.sevenpeakssoftware.amirnaghavi.base

import com.twistedequations.rx2.AndroidRxSchedulers
import io.reactivex.Scheduler
import javax.inject.Inject

interface SchedulerProvider {

    val ioScheduler: Scheduler
    val mainScheduler: Scheduler
    val computation: Scheduler

}


class AppSchedulerProvider @Inject constructor(
        androidRxSchedulers: AndroidRxSchedulers
) : SchedulerProvider {
    override val ioScheduler: Scheduler = androidRxSchedulers.io()
    override val mainScheduler: Scheduler = androidRxSchedulers.mainThread()
    override val computation: Scheduler = androidRxSchedulers.computation()

}