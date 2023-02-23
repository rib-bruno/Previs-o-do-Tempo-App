package com.example.previsodotempoapp.domain.weather

import java.time.LocalDateTime

//dados climáticos específicos para uma determinada hora
class WeatherData (
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressure: Double,
    val windSpeed: Double,
    val humidity: Double,
    val weatherType: WeatherType
        )