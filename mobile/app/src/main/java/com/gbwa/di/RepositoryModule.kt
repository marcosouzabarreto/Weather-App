package com.gbwa.di

import com.gbwa.data.repository.WeatherRepositoryImplementation
import com.gbwa.domain.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)

abstract class RepositoryModule {

    @Binds
    @Singleton

    abstract fun bindWeatherRepository(weatherRepositoryImplementation: WeatherRepositoryImplementation): WeatherRepository
}

