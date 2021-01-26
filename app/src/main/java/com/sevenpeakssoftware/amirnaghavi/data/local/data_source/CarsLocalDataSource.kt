package com.sevenpeakssoftware.amirnaghavi.data.local.data_source

import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.data.local.CarDao
import com.sevenpeakssoftware.amirnaghavi.data.local.data.CarItemLocalEntity

import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import io.reactivex.Observable
import javax.inject.Inject

class CarsLocalDataSource @Inject constructor(
        private val dao: CarDao,
        private val mapper: TwoWayMapper<List<CarEntity>, List<CarItemLocalEntity>>
): ObservableReadableAndWriteable<List<CarEntity>, CarsParam>(){

    override fun read(param: CarsParam): Observable<Answer<List<CarEntity>>> = dao.getCars().map {
        mapper.mapRightToLeft(it).toSuccessAnswer()
    }

    override fun write(input: List<CarEntity>) =dao.insertCars(
        mapper.mapLeftToRight(input)
    )

}