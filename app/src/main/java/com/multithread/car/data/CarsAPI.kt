package com.multithread.car.data

import com.multithread.car.data.dto.CarDTO
import io.reactivex.Observable
import retrofit2.http.GET

interface CarsAPI{
    @GET("article/get_articles_list")
    fun getCarData(): Observable<CarDTO>
}