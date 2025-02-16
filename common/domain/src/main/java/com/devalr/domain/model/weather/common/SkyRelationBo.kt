package com.devalr.domain.model.weather.common

import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.enums.WeatherTime

data class SkyRelationBo(
    val skyState: SkyState,
    val time: WeatherTime
)
