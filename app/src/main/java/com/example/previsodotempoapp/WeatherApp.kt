package com.example.previsodotempoapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//saber de onde ele tem que obter esse contexto
@HiltAndroidApp
class WeatherApp: Application() {
}