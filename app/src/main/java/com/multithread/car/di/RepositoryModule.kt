package com.multithread.car.di


import com.multithread.car.data.local.data_source.CarsLocalDataSourceAlias
import com.multithread.car.data.remote.RemoteCarsDataSourceAlias
import com.multithread.car.domain.repository.CarListRepository
import com.multithread.car.domain.repository.CarListRepositoryAlias
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideCarRepository(
            localDataSource: CarsLocalDataSourceAlias,
            remoteCarsDataSource: RemoteCarsDataSourceAlias
    ): CarListRepositoryAlias =
            CarListRepository(localDataSource, remoteCarsDataSource)
}
