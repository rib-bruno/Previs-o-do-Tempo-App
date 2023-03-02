package com.example.previsodotempoapp.di

import com.example.previsodotempoapp.data.location.DefaultLocationTracker
import com.example.previsodotempoapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)

abstract class LocationModule {

    @Binds
    @Singleton
    //vincular uma instância específica
    abstract fun bindLocationTracker(defaultLocationTracker: DefaultLocationTracker) : LocationTracker
}