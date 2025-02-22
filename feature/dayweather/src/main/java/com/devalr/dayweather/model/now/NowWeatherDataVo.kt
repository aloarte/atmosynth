package com.devalr.dayweather.model.now

import com.devalr.framework.enums.AnimationsType

data class NowWeatherDataVo(
    val temperature: WeatherMaxMin,
    val thermalSensation: WeatherMaxMin,
    val humidity: WeatherMaxMin,
    val skyAnimation: AnimationsType,
    val wind: WindState
) {

    fun mutateValues(newTemperature: String?, newThermalSensation: String?) =
        if (newTemperature != null && newThermalSensation != null) {
            this.copy(
                temperature = temperature.copy(current = newTemperature),
                thermalSensation = thermalSensation.copy(current = newThermalSensation)
            )
        } else this
}
