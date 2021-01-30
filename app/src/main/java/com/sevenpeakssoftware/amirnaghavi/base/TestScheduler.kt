package com.sevenpeakssoftware.amirnaghavi.base

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestScheduler : SchedulerProvider{
    override val ioScheduler: Scheduler
        get() = Schedulers.trampoline()
    override val mainScheduler: Scheduler
        get() = Schedulers.trampoline()
    override val computation: Scheduler
        get() = Schedulers.trampoline()
}