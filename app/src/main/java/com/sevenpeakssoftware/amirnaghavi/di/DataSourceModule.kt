package com.sevenpeakssoftware.amirnaghavi.di

import android.media.MediaDataSource
import com.sevenpeakssoftware.amirnaghavi.base.BaseDataSource
import com.sevenpeakssoftware.amirnaghavi.base.CarsParam
import com.sevenpeakssoftware.amirnaghavi.base.ObservableReadable
import com.sevenpeakssoftware.amirnaghavi.base.ObservableReadableAndWriteable
import com.sevenpeakssoftware.amirnaghavi.data.local.data_source.CarsLocalDataSource
import com.sevenpeakssoftware.amirnaghavi.data.remote.RemoteCarsDataSource
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import dagger.Binds
import dagger.Module

@Module
interface DataSourceModule {

    @Binds
    fun bindsCarRemoteDataSource(dataSource: RemoteCarsDataSource): ObservableReadable<List<CarEntity>, CarsParam>

    @Binds
    fun bindsLocalDataSource(dataSource: CarsLocalDataSource): ObservableReadableAndWriteable<List<CarEntity>, CarsParam>
}