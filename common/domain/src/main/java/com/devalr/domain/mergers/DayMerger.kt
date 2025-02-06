package com.devalr.domain.mergers

import com.devalr.domain.model.weather.HourlyDataBo
import com.devalr.domain.model.weather.RainRelationBo
import com.devalr.domain.model.weather.TemperatureRelationBo

class DayMerger {
    fun merge(
        temperatureList: List<TemperatureRelationBo>,
        rainList: List<RainRelationBo>,
    ): List<HourlyDataBo> =
        temperatureList.mapNotNull { temperatureRelation ->
            rainList.find { it.time == temperatureRelation.time }?.let { rainRelation ->
                HourlyDataBo(
                    rainProbability = rainRelation.rainProbability,
                    temperature = temperatureRelation.temperature,
                    time = temperatureRelation.time,
                )
            }
        }
}
