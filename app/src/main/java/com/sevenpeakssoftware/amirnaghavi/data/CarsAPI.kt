package com.sevenpeakssoftware.amirnaghavi.data

import com.sevenpeakssoftware.amirnaghavi.data.dto.CarsDTO
import io.reactivex.Observable
import retrofit2.http.GET

interface CarsAPI{
    @GET("article/get_articles_list")
    fun getCarData(): Observable<CarsDTO>
}