package com.example.previsodotempoapp.domain.location

import android.location.Location

interface LocationTracker {
    //fornecer a localização atual
    suspend fun getCurrentLocation() : Location?
}