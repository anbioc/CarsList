package com.sevenpeakssoftware.amirnaghavi.di

import com.sevenpeakssoftware.amirnaghavi.base.CarsParam
import com.sevenpeakssoftware.amirnaghavi.base.domain.ErrorContainer
import com.sevenpeakssoftware.amirnaghavi.base.domain.ObservableUseCase
import com.sevenpeakssoftware.amirnaghavi.base.SchedulerProvider
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.domain.repository.CarListRepository
import com.sevenpeakssoftware.amirnaghavi.domain.usecase.CarsUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideCarUseCase(
            carsRepository: CarListRepository,
            scheduler: SchedulerProvider,
            errorContainer: ErrorContainer
    ): ObservableUseCase<List<CarEntity>, CarsParam> =
            CarsUseCase(carsRepository, scheduler, errorContainer)
}
