package com.multithread.car.di

import com.multithread.car.base.CarsParam
import com.multithread.car.base.domain.ErrorContainer
import com.multithread.car.base.domain.ObservableUseCase
import com.multithread.car.base.SchedulerProvider
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.domain.repository.CarListRepository
import com.multithread.car.domain.usecase.CarsUseCase
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
