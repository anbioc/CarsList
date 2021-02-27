package com.multithread.car.data.local.data_source

import com.multithread.car.base.*
import com.multithread.car.base.domain.Answer
import com.multithread.car.base.domain.ErrorEntity
import com.multithread.car.base.domain.toSuccessAnswer
import com.multithread.car.data.local.CarDao
import com.multithread.car.data.local.data.CarItemLocalEntity

import com.multithread.car.domain.entity.CarEntity
import io.reactivex.Observable
import javax.inject.Inject

class CarsLocalDataSource @Inject constructor(
        private val dao: CarDao,
        private val mapper: TwoWayMapper<List<CarEntity>, List<CarItemLocalEntity>>
) : StrategyObservableReadableAndWriteableDataSource<List<CarEntity>, List<CarEntity>, CarsParam> {

    override fun read(param: CarsParam): Observable<Answer<List<CarEntity>>> = dao.getCars().map {
        if (it.isEmpty()) {
            Answer.Failure(ErrorEntity.NotFound)
        } else {
            mapper.mapRightToLeft(it).toSuccessAnswer()
        }
    }

    override fun write(data: List<CarEntity>, param: CarsParam) = dao.insertCars(
            mapper.mapLeftToRight(data)
    )

}