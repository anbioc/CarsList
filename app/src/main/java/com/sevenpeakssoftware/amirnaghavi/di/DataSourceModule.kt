package com.sevenpeakssoftware.amirnaghavi.di


import com.sevenpeakssoftware.amirnaghavi.base.*
import com.sevenpeakssoftware.amirnaghavi.data.CarsAPI
import com.sevenpeakssoftware.amirnaghavi.data.dto.CarDTO
import com.sevenpeakssoftware.amirnaghavi.data.local.CarDao
import com.sevenpeakssoftware.amirnaghavi.data.local.data.CarItemLocalEntity
import com.sevenpeakssoftware.amirnaghavi.data.local.data_source.CarsLocalDataSource
import com.sevenpeakssoftware.amirnaghavi.data.remote.RemoteCarsDataSource
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import dagger.Module
import dagger.Provides

@Module
class DataSourceModule {

    @Provides
    fun bindsCarRemoteDataSource(
            api: CarsAPI, mapper: Mapper<CarDTO, List<CarEntity>>
    ): ObservableReadable<List<CarEntity>, CarsParam> =
            RemoteCarsDataSource(api, mapper)

    @Provides
    fun bindsLocalDataSource(
            dao: CarDao, mapper: TwoWayMapper<List<CarEntity>, List<CarItemLocalEntity>>
    ): ObservableReadableAndWriteable<List<CarEntity>, CarsParam> =
            CarsLocalDataSource(dao, mapper)
}