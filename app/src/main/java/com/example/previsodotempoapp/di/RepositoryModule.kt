package com.example.previsodotempoapp.di

import android.app.Application
import com.example.previsodotempoapp.data.remote.WeatherApi
import com.example.previsodotempoapp.data.repository.WeatherRepositoryImpl
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton
@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
  abstract  fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ) : RepositoryModule
}