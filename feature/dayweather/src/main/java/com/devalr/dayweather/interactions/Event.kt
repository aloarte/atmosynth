package com.devalr.dayweather.interactions

import com.devalr.dayweather.model.now.WeatherMaxMin

sealed class Event {
    data object LoadScreen : Event()
    data object OnRetryDailySummaryPrompt : Event()
    data object ChangeCity : Event()
    data class OnStartHumidityDetail(val humidityData: WeatherMaxMin?) : Event()
    data class OnChangeHumidityDetailVisibility(val isVisible: Boolean) : Event()
    data class OnUploadErrorState(val error: Boolean) : Event()
    data class OnUploadLoadingState(val loading: Boolean) : Event()

}
