package com.devalr.dayweather.interactions

data class BooleanLoadingStates(
    val displayWeatherSummaryDetail: Boolean = false,
    val displayPrecipitationsDetail: Boolean = false,
    val displayWindDetail: Boolean = false,
    val displayHumidityDetail: Boolean = false,
    val displayUvDetail: Boolean = false,
    val loadingWeather: Boolean = false,
    val errorReceived: Boolean = false
)
