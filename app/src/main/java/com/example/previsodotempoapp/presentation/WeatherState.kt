package com.example.previsodotempoapp.presentation

import com.example.previsodotempoapp.domain.weather.WeatherInfo

data class WeatherState(
    //ainda precisamos recuperar
    val weatherInfo: WeatherInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null


)
