package com.sevenpeakssoftware.amirnaghavi.di

import android.app.Application
import android.content.Context
import com.sevenpeakssoftware.amirnaghavi.base.AppSchedulerProvider
import com.sevenpeakssoftware.amirnaghavi.base.SchedulerProvider
import com.twistedequations.rx2.AndroidRxSchedulers
import com.twistedequations.rx2.DefaultAndroidRxSchedulers
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AndroidModule {

    @Singleton
    @Provides
    fun rxSchedulers() : AndroidRxSchedulers = DefaultAndroidRxSchedulers()


    @Singleton
    @Provides
    fun provideAppScheduler(rxSchedulers: AndroidRxSchedulers): SchedulerProvider =
            AppSchedulerProvider(rxSchedulers)

    @Provides
    fun provideContext(application: Application): Context = application.applicationContext
}
