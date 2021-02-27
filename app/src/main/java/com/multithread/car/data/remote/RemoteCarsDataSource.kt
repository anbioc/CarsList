package com.multithread.car.data.remote

import com.multithread.car.BuildConfig
import com.multithread.car.base.*
import com.multithread.car.base.domain.Answer
import com.multithread.car.base.domain.toSuccessAnswer
import com.multithread.car.data.CarsAPI
import com.multithread.car.data.dto.CarDTO
import com.multithread.car.domain.entity.CarEntity
import io.reactivex.Observable
import javax.inject.Inject

class RemoteCarsDataSource @Inject constructor(
    private val fakeDataHelper:  ObservableReadable<List<CarEntity>, CarsParam>,
    private val api: CarsAPI,
    private val mapper: Mapper<CarDTO, List<CarEntity>>
) : StrategyObservableReadableDataSource<List<CarEntity>, CarsParam> {

    override fun read(param: CarsParam): Observable<Answer<List<CarEntity>>> =
        if (BuildConfig.IS_DEMO){
            fakeDataHelper.read(param)
        }else {
            api.getCarData().map {
                mapper.map(it).toSuccessAnswer()
            }
        }


}