package com.multithread.car.domain.repository

import com.multithread.car.base.*
import com.multithread.car.domain.entity.CarEntity
import javax.inject.Inject

typealias CarListRepositoryAlias = ObservableStrategyRepository<List<CarEntity>, List<CarEntity>, CarsParam>

class CarListRepository @Inject constructor(
        localDataSource: StrategyObservableReadableAndWriteableDataSource<List<CarEntity>, List<CarEntity>, CarsParam>,
       remoteCarsDataSource: StrategyObservableReadableDataSource<List<CarEntity>, CarsParam>
): CarListRepositoryAlias(
        localDataSource, remoteCarsDataSource
)
