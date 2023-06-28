package com.gbwa.data.mappers

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.gbwa.data.remote.WeatherDTO
import com.gbwa.data.remote.WeatherDataDTO
import com.gbwa.domain.util.REQUESTS_PER_DAY
import com.gbwa.domain.weather.WeatherData
import com.gbwa.domain.weather.WeatherInfo
import com.gbwa.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val data: WeatherData
)

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDataDTO.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val windSpeed = windSpeeds[index]
        val weatherCode = weatherCodes[index]
        val humidity = humidities[index]
        val pressure = pressures[index]
        IndexedWeatherData(
            index, WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                pressureLevel = pressure,
                humidity = humidity,
                windSpeed = windSpeed,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index / REQUESTS_PER_DAY
    }.mapValues { it ->
        it.value.map { it.data }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun WeatherDTO.toWeatherInfo(): WeatherInfo {
    val weatherDataMap = weatherData.toWeatherDataMap()
    val now = LocalDateTime.now()

    val currentWeatherData = weatherDataMap[0]?.find {
        val hour = if (now.minute < 30 || now.hour == 23) now.hour else now.hour + 1
        Log.i("WeatherDTO", "toWeatherInfo: ${it.time.hour} == ${now.hour}")
        it.time.hour == hour
    }
    return WeatherInfo(
        weatherDataPerDay = weatherDataMap,
        currentWeatherData = currentWeatherData
    )

}