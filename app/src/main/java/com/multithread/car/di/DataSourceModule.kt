package com.multithread.car.di


import com.multithread.car.base.*
import com.multithread.car.data.CarsAPI
import com.multithread.car.data.dto.CarDTO
import com.multithread.car.data.local.CarDao
import com.multithread.car.data.local.data.CarItemLocalEntity
import com.multithread.car.data.local.data_source.CarsLocalDataSource
import com.multithread.car.data.local.fake.FakeDataHelper
import com.multithread.car.data.remote.RemoteCarsDataSource
import com.multithread.car.domain.entity.CarEntity
import dagger.Module
import dagger.Provides
import javax.inject.Named

@Module
class DataSourceModule {

    @Provides
    @Named("fakeDataHelper")
    fun bindFakeDataHelper(): ObservableReadable<List<CarEntity>, CarsParam> = FakeDataHelper()

    @Provides
    fun bindsCarRemoteDataSource(
        @Named("fakeDataHelper") fakeDataHelper: ObservableReadable<List<CarEntity>, CarsParam>,
        api: CarsAPI,
        mapper: Mapper<CarDTO, List<CarEntity>>
    ): ObservableReadable<List<CarEntity>, CarsParam> =
        RemoteCarsDataSource(fakeDataHelper, api, mapper)

    @Provides
    fun bindsLocalDataSource(
        dao: CarDao, mapper: TwoWayMapper<List<CarEntity>, List<CarItemLocalEntity>>
    ): StrategyObservableReadableAndWriteableDataSource<List<CarEntity>, List<CarEntity>, CarsParam> =
        CarsLocalDataSource(dao, mapper)
}