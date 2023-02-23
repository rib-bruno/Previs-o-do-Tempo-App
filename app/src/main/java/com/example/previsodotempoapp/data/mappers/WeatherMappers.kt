package com.example.previsodotempoapp.data.mappers

import com.example.previsodotempoapp.data.remote.WeatherDataDto
import com.example.previsodotempoapp.data.remote.WeatherDto
import com.example.previsodotempoapp.domain.weather.WeatherData
import com.example.previsodotempoapp.domain.weather.WeatherInfo
import com.example.previsodotempoapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


//encaminhar os parametros de index
private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val windSpeed = windSpeeds[index]
        val pressure = pressures[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            data = WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressure = pressure,
                windSpeed = windSpeed,
                humidity = humidity,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / 24
        //mapear os valores indexados com o data, mapeando novamente para dados meterológiocs normais
    }.mapValues {
        it.value.map { it.data }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()
    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = when {
            now.minute < 30 -> now.hour
            now.hour == 23 -> 12.00
            else -> now.hour + 1
        }
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )
}

//private fun daysBetweenDateAndNow(dateTime: LocalDateTime): Int {
//    val dateStartOfDay = dateTime.toLocalDate().atStartOfDay()
//    val nowStartOfDay = LocalDate.now().atStartOfDay()
//    return Duration.between(nowStartOfDay, dateStartOfDay).toDays().toInt()
//}
//
//
//
//
//fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
//    return time.mapIndexed { index, time ->
//        val temperature = temperatures[index]
//        val weatherCode = weatherCodes[index]
//        val windSpeed = windSpeeds[index]
//        val pressure = pressures[index]
//        val humidity = humidities[index]
//        WeatherData(
//            time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
//            temperatureCelsius = temperature,
//            pressure = pressure,
//            windSpeed = windSpeed,
//            humidity = humidity,
//            weatherType = WeatherType.fromWMO(weatherCode)
//        )
//    }.groupBy {
//        daysBetweenDateAndNow(it.time)
//    }
//
//}