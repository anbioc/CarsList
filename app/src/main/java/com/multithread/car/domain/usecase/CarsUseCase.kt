package com.multithread.car.domain.usecase

import com.multithread.car.base.*
import com.multithread.car.base.domain.Answer
import com.multithread.car.base.domain.ErrorContainer
import com.multithread.car.base.domain.ObservableUseCase
import com.multithread.car.base.domain.RepositoryStrategy
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.domain.repository.CarListRepository
import io.reactivex.Observable
import javax.inject.Inject

class CarsUseCase @Inject constructor(
    private val carsRepository: CarListRepository,
    scheduler: SchedulerProvider,
    errorContainer: ErrorContainer
) : ObservableUseCase<List<CarEntity>, CarsParam>(scheduler, errorContainer) {
    override fun buildObservable(
        params: CarsParam,
        strategy: RepositoryStrategy
    ): Observable<Answer<List<CarEntity>>> = carsRepository.getResult(params, strategy)
}