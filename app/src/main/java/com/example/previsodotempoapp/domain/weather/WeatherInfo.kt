package com.example.previsodotempoapp.domain.weather

//dados meteorógicos por dia
data class WeatherInfo(
    val weatherDataPerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?
)
