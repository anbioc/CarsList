package com.sevenpeakssoftware.amirnaghavi.presentation

import com.sevenpeakssoftware.amirnaghavi.presentation.car.CarFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface NavigationActivityBinding {
    @ContributesAndroidInjector
    fun bindsNavigationActivity(): NavigationActivity
}

@Module
interface CarFragmentBinding {
    @ContributesAndroidInjector
    fun bindsCarsFragment(): CarFragment
}