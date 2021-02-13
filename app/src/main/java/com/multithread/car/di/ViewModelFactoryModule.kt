package com.multithread.car.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.multithread.car.presentation.car.CarsViewModel
import com.multithread.car.util.AppViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

    @Binds
    @IntoMap
    @ViewModelKey(CarsViewModel::class)
    abstract fun bindCarViewModel(viewModel: CarsViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory

}
