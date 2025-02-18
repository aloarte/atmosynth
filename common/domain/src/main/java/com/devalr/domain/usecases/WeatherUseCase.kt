package com.devalr.domain.usecases

import com.devalr.domain.model.FetchedWeatherData
import com.devalr.domain.repositories.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async

class WeatherUseCase(private val weatherRepository: WeatherRepository) {

    data class Params(val cityCode: String)

    class WeatherUseCaseException(override val message: String) : Throwable()

    suspend fun execute(scope: CoroutineScope, params: Params): Result<FetchedWeatherData> {
        try {

        } catch (exception: WeatherUseCaseException) {
            Result.Error(Exception(exception.message))
        }

        val hourlyWeatherData = scope.async {
            weatherRepository.fetchHourlyWeather(params.cityCode).also {
                if (it == null) throw WeatherUseCaseException(message = "Hourly weather data was null")
            }
        }
        val dailyWeatherData = scope.async {
            weatherRepository.fetchDailyWeather(params.cityCode).also {
                if (it == null) throw WeatherUseCaseException(message = "Daily weather data was null")
            }
        }
        return Result.Success(
            FetchedWeatherData(
                hourlyData = hourlyWeatherData.await()!!,
                dailyData = dailyWeatherData.await()!!
            )
        )

    }
}