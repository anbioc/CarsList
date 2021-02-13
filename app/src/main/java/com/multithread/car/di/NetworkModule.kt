package com.multithread.car.di

import androidx.annotation.NonNull
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.multithread.car.BuildConfig
import com.multithread.car.base.domain.ErrorContainer
import com.multithread.car.data.CarsAPI
import com.multithread.car.util.GeneralHandlerImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    fun provideErrorHandler(errorHandler: GeneralHandlerImpl): ErrorContainer = errorHandler

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .cache(null)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }).build()

    @Provides
    @Singleton
    fun provideRetrofit(
            @NonNull client: OkHttpClient): Retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    fun provideCarService(@NonNull retrofit: Retrofit): CarsAPI = retrofit.create(CarsAPI::class.java)
}