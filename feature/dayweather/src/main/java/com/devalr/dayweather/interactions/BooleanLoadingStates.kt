package com.devalr.dayweather.interactions

data class BooleanLoadingStates(
    val displayHumidityDetail: Boolean = false,
    val loadingWeather: Boolean = false,
    val errorReceived: Boolean = false
)
