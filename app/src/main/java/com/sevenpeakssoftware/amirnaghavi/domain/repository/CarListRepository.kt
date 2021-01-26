package com.sevenpeakssoftware.amirnaghavi.domain.repository

import com.sevenpeakssoftware.amirnaghavi.base.Answer
import com.sevenpeakssoftware.amirnaghavi.base.CarsParam
import com.sevenpeakssoftware.amirnaghavi.base.ObservableRepository
import com.sevenpeakssoftware.amirnaghavi.data.local.data_source.CarsLocalDataSource
import com.sevenpeakssoftware.amirnaghavi.data.remote.RemoteCarsDataSource
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import io.reactivex.Observable
import javax.inject.Inject

class CarListRepository @Inject constructor(
    private val localDataSource: CarsLocalDataSource,
    private val remoteCarsDataSource: RemoteCarsDataSource
): ObservableRepository<List<CarEntity>, CarsParam>(){
    override fun getOffline(param: CarsParam): Observable<Answer<List<CarEntity>>> =
        localDataSource.read(param)

    override fun getRemote(param: CarsParam): Observable<Answer<List<CarEntity>>> =
        remoteCarsDataSource.read(param)
}