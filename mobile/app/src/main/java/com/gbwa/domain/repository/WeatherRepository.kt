package com.gbwa.domain.repository

import com.gbwa.domain.util.Resource
import com.gbwa.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lon: Double): Resource<WeatherInfo>
}