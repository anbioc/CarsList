package com.sevenpeakssoftware.amirnaghavi.data.remote

import android.util.Log
import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.data.CarsAPI
import com.sevenpeakssoftware.amirnaghavi.data.dto.CarDTO
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import io.reactivex.Observable
import javax.inject.Inject

class RemoteCarsDataSource @Inject constructor(
    private val api: CarsAPI,
    private val mapper: Mapper<CarDTO, List<CarEntity>>
) : ObservableReadable<List<CarEntity>, CarsParam>() {
    override fun read(param: CarsParam): Observable<Answer<List<CarEntity>>> =
        api.getCarData().map {
            Log.d("dataTag", "read: got data ${it.content}")
        mapper.map(it).toSuccessAnswer()
    }

}