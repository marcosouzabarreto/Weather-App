package com.gbwa.data.repository

import com.gbwa.data.mappers.toWeatherInfo
import com.gbwa.data.remote.WeatherApi
import com.gbwa.domain.repository.WeatherRepository
import com.gbwa.domain.util.Resource
import com.gbwa.domain.weather.WeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class WeatherRepositoryImplementation @Inject constructor(private val api: WeatherApi) :
    WeatherRepository {

    override suspend fun getWeatherData(lat: Double, lon: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(api.getWeatherData(lat, lon).toWeatherInfo())
        } catch (e: Exception) {

            Resource.Error(e.message ?: "An error occurred")
        }
    }
}