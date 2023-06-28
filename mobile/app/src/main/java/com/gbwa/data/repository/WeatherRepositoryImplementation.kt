package com.gbwa.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.gbwa.data.mappers.toWeatherInfo
import com.gbwa.data.remote.WeatherApi
import com.gbwa.domain.repository.WeatherRepository
import com.gbwa.domain.util.Resource
import com.gbwa.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(private val api: WeatherApi) :
    WeatherRepository {

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeatherData(lat: Double, lon: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(api.getWeatherData(lat, lon).toWeatherInfo())
        } catch (e: Exception) {

            Resource.Error(e.message ?: "An error occurred")
        }
    }
}