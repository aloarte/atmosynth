package com.devalr.domain.model.weather

import com.devalr.domain.model.SkyState
import com.devalr.domain.model.WeatherTime

data class SkyRelationBo(
    val skyState: SkyState,
    val time: WeatherTime
)
