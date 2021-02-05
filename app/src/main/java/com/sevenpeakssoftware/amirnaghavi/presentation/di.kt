package com.sevenpeakssoftware.amirnaghavi.presentation

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.base.domain.ObservableUseCase
import com.sevenpeakssoftware.amirnaghavi.base.presentation.CompositeEventHandler
import com.sevenpeakssoftware.amirnaghavi.base.presentation.EventHandler
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarFragment
import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarState
import com.sevenpeakssoftware.amirnaghavi.presentation.car.GetCarInfoEvent
import com.sevenpeakssoftware.amirnaghavi.presentation.car.handler.CarEventHandler
import com.sevenpeakssoftware.amirnaghavi.presentation.car.handler.CarEventHandlerManager
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
