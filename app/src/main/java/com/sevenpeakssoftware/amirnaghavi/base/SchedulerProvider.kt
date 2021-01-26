package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Scheduler

interface SchedulerProvider {

    val ioScheduler: Scheduler
    val mainScheduler: Scheduler
    val computation: Scheduler

}