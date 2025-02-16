package com.devalr.dayweather.model

import com.devalr.dayweather.model.enums.SkyStateIcon
import java.time.LocalDateTime

data class HourlyWeatherVo(
    override val hour: String,
    override val completeTime: LocalDateTime,
    val skyState: SkyStateIcon,
    val humidity: String,
    val precipitationProbability: String,
    val temperature: String,
    val thermalSensation: String
) : HourlyDataVo()
