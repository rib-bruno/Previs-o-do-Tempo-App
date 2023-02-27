package com.example.previsodotempoapp.domain.repository

import com.example.previsodotempoapp.domain.util.Resource
import com.example.previsodotempoapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double) : Resource<WeatherInfo>
}