package com.multithread.car.di

import com.multithread.car.base.CarsParam
import com.multithread.car.base.domain.ObservableRepository
import com.multithread.car.data.local.data_source.CarsLocalDataSource
import com.multithread.car.data.remote.RemoteCarsDataSource
import com.multithread.car.domain.entity.CarEntity
import com.multithread.car.domain.repository.CarListRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideCarRepository(
            localDataSource: CarsLocalDataSource,
            remoteCarsDataSource: RemoteCarsDataSource
    ): ObservableRepository<List<CarEntity>, CarsParam> =
            CarListRepository(localDataSource, remoteCarsDataSource)
}
