package com.sevenpeakssoftware.amirnaghavi

import com.sevenpeakssoftware.amirnaghavi.di.AppComponent
import com.sevenpeakssoftware.amirnaghavi.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class CarListApp : DaggerApplication() {

    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> = appComponent

}