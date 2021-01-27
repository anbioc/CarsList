package com.sevenpeakssoftware.amirnaghavi.domain.usecase

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.domain.repository.CarListRepository
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