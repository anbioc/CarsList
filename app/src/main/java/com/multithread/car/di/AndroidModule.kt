package com.multithread.car.di

import android.app.Application
import android.content.Context
import com.multithread.car.base.AppSchedulerProvider
import com.multithread.car.base.SchedulerProvider
import com.multithread.car.util.StringResourceHolder
import com.multithread.car.util.StringResourceHolderImpl
import com.twistedequations.rx2.AndroidRxSchedulers
import com.twistedequations.rx2.DefaultAndroidRxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule {

    @Provides
    fun provideStringHolder(context: Context): StringResourceHolder =
            StringResourceHolderImpl(context)

    @Singleton
    @Provides
    fun rxSchedulers(): AndroidRxSchedulers = DefaultAndroidRxSchedulers()


    @Singleton
    @Provides
    fun provideAppScheduler(rxSchedulers: AndroidRxSchedulers): SchedulerProvider =
            AppSchedulerProvider(rxSchedulers)

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}
