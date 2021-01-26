package com.sevenpeakssoftware.amirnaghavi.data.remote

import com.sevenpeakssoftware.amirnaghavi.base.Answer
import com.sevenpeakssoftware.amirnaghavi.base.Mapper
import com.sevenpeakssoftware.amirnaghavi.base.ObservableReadable
import com.sevenpeakssoftware.amirnaghavi.base.toSuccess
import com.sevenpeakssoftware.amirnaghavi.data.CarsAPI
import com.sevenpeakssoftware.amirnaghavi.data.dto.CarsDTO
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import io.reactivex.Observable
import javax.inject.Inject

class RemoteCarsDataSource @Inject constructor(
    private val api: CarsAPI,
    private val mapper: Mapper<CarsDTO, CarEntity>
) : ObservableReadable<CarEntity>() {
    override fun read(): Observable<Answer<CarEntity>> = api.getCarData().map {
        mapper.map(it).toSuccess()
    }

}