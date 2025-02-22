package com.devalr.dayweather.interactions

data class BooleanLoadingStates(
    val displayWindDetail: Boolean = false,
    val displayHumidityDetail: Boolean = false,
    val loadingWeather: Boolean = false,
    val errorReceived: Boolean = false
)
