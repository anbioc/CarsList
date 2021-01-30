package com.sevenpeakssoftware.amirnaghavi.di

import android.content.Context
import androidx.annotation.NonNull
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.sevenpeakssoftware.amirnaghavi.BuildConfig
import com.sevenpeakssoftware.amirnaghavi.base.ErrorContainer
import com.sevenpeakssoftware.amirnaghavi.data.CarsAPI
import com.sevenpeakssoftware.amirnaghavi.util.GeneralHandlerImpl
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
    fun provideHttpClient(context: Context): OkHttpClient =
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