package com.example.previsodotempoapp.data.repository

import com.example.previsodotempoapp.data.mappers.toWeatherInfo
import com.example.previsodotempoapp.data.remote.WeatherApi
import com.example.previsodotempoapp.domain.repository.WeatherRepository
import com.example.previsodotempoapp.domain.util.Resource
import com.example.previsodotempoapp.domain.weather.WeatherInfo
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor (
    private val api: WeatherApi
        ) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                    lat = lat,
                    long = long
                ).toWeatherInfo()
            )
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred")
        }
    }
}