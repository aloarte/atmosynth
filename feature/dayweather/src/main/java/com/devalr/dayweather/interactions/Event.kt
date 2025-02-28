package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.hourly.HourlyWeatherVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.dayweather.model.now.WindState

sealed class Event {
    data object LoadScreen : Event()
    data object ChangeCity : Event()

    // Events that start the fetch of the AI data for every card
    data object OnStartDailySummaryDetail : Event()
    data class OnStartPrecipitationsDetail(val hourlyTime: List<HourlyWeatherVo>) : Event()
    data class OnStartHumidityDetail(
        val humidityData: WeatherMaxMin?,
        val temperatureData: WeatherMaxMin?
    ) : Event()

    data class OnStartWindDetail(val wind: WindState?) : Event()
    data class OnStartUvDetail(val uv: String) : Event()

    // Events to change visibility of bottom sheets
    data class OnUploadWeatherDetailVisibility(val isVisible: Boolean) : Event()
    data class OnUploadPrecipitationsDetailVisibility(val isVisible: Boolean) : Event()
    data class OnUploadHumidityDetailVisibility(val isVisible: Boolean) : Event()
    data class OnUploadWindDetailVisibility(val isVisible: Boolean) : Event()
    data class OnUploadUvDetailVisibility(val isVisible: Boolean) : Event()

    // General loading states
    data class OnUploadErrorState(val error: Boolean) : Event()
    data class OnUploadLoadingState(val loading: Boolean) : Event()

}
