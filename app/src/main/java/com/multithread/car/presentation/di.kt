package com.multithread.car.presentation

import com.multithread.car.base.*
import com.multithread.car.base.domain.ObservableUseCase
import com.multithread.car.base.presentation.CompositeEventHandler
import com.multithread.car.base.presentation.EventHandler
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.presentation.car.CarFragment
import com.multithread.car.presentation.car.GetCarInfoEvent
import com.multithread.car.presentation.car.handler.CarEventHandler
import com.multithread.car.presentation.car.handler.CarEventHandlerManager
import com.multithread.car.presentation.car.state.CarState
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

@Module
interface NavigationActivityBinding {
    @ContributesAndroidInjector
    fun bindsNavigationActivity(): NavigationActivity

    companion object {

        @Provides
        fun provideCarEventHandler(
                schedulerProvider: SchedulerProvider,
                useCase: ObservableUseCase<List<CarEntity>, CarsParam>,
        ): EventHandler<GetCarInfoEvent, CarState, CarsParam, List<CarEntity>> =
                CarEventHandler(useCase, schedulerProvider)

        @Provides
        fun provideCarEventCompositeHandler(
                carEventHandler: EventHandler<GetCarInfoEvent, CarState, CarsParam, List<CarEntity>>
        ): CompositeEventHandler<CarState, CarsParam> = CarEventHandlerManager().apply {
            addHandler(carEventHandler)
        }
    }
}

@Module
interface CarFragmentBinding {
    @ContributesAndroidInjector()
    fun bindsCarsFragment(): CarFragment
}
