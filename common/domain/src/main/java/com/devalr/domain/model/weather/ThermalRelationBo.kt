package com.devalr.domain.model.weather

import com.devalr.domain.model.enums.WeatherTime

data class ThermalRelationBo(
    val thermalSensation: Int,
    val time: WeatherTime
)
