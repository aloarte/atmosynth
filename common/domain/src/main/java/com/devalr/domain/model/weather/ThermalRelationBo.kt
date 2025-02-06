package com.devalr.domain.model.weather

import com.devalr.domain.model.WeatherTime

data class ThermalRelationBo(
    val thermalSensation: Int,
    val time: WeatherTime,
)
