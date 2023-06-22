package com.gbwa.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val pressureLevel: Double,
    val humidity: Double,
    val windSpeed: Double,
    val weatherType: WeatherType,
)
