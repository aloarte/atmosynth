package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.dayweather.model.now.WindState

sealed class Event {
    data object LoadScreen : Event()
    data object OnRetryDailySummaryPrompt : Event()
    data object ChangeCity : Event()
    data class OnStartHumidityDetail(val humidityData: WeatherMaxMin?,val temperatureData: WeatherMaxMin?) : Event()
    data class OnStartWindDetail(val wind: WindState?) : Event()
    data class OnStartUvDetail(val uv: String) : Event()
    data class OnUploadHumidityDetailVisibility(val isVisible: Boolean) : Event()
    data class OnUploadWindDetailVisibility(val isVisible: Boolean) : Event()
    data class OnUploadUvDetailVisibility(val isVisible: Boolean) : Event()
    data class OnUploadErrorState(val error: Boolean) : Event()
    data class OnUploadLoadingState(val loading: Boolean) : Event()

}
