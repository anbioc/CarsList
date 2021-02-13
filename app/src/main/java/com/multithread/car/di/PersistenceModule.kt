package com.multithread.car.di

import android.content.Context
import com.multithread.car.data.local.CarDao
import com.multithread.car.data.local.CarsDataBase
import dagger.Module
import dagger.Provides

@Module
class PersistenceModule {

    @Provides
    fun provideDataBase(context: Context): CarsDataBase = CarsDataBase.create(context)

    @Provides
    fun provideCarDao(carsDataBase: CarsDataBase):CarDao = carsDataBase.provideCarDao()

}