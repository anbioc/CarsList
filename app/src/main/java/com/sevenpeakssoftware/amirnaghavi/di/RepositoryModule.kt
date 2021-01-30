package com.sevenpeakssoftware.amirnaghavi.di

import com.sevenpeakssoftware.amirnaghavi.base.CarsParam
import com.sevenpeakssoftware.amirnaghavi.base.ObservableRepository
import com.sevenpeakssoftware.amirnaghavi.data.local.data_source.CarsLocalDataSource
import com.sevenpeakssoftware.amirnaghavi.data.remote.RemoteCarsDataSource
import com.sevenpeakssoftware.amirnaghavi.domain.entity.CarEntity
import com.sevenpeakssoftware.amirnaghavi.domain.repository.CarListRepository
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
