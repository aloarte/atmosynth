package com.devalr.dayweather.mergers

import com.devalr.dayweather.model.HourlyDataVo
import com.devalr.dayweather.model.HourlyEventVo
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.domain.HourlyEventData
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.DailyPredictionBo
import com.devalr.domain.model.weather.HourlyWeatherBo
import java.time.LocalDateTime

class HourlyMerger(
    private val eventMapper: Mapper<HourlyEventData, HourlyEventVo>,
    private val hourlyMapper: Mapper<HourlyWeatherBo, HourlyWeatherVo>
) {
    fun merge(
        dailyPredictionBo: List<DailyPredictionBo>,
        initBracket: Long,
        endBracket: Long
    ): List<HourlyDataVo> {
        if (initBracket > endBracket || initBracket < 1) {
            return emptyList()
        }
        val allHoursList = mutableListOf<HourlyDataVo>()
        dailyPredictionBo.forEach {
            allHoursList += merge(it.hourlyData, it.sunEvents)
        }
        val now = LocalDateTime.now()
        val prev2Hours = now.minusHours(initBracket)
        val next24Hours = now.plusHours(endBracket)
        return allHoursList.filter {
            it.completeTime.isAfter(prev2Hours) &&
                it.completeTime.isBefore(next24Hours)
        }
    }

    private fun merge(
        hourlyWeatherData: List<HourlyWeatherBo>,
        events: List<HourlyEventData>
    ): List<HourlyDataVo> =
        mutableListOf<HourlyDataVo>().apply {
            addAll(events.map { eventMapper.transform(it) })
            addAll(hourlyWeatherData.map { hourlyMapper.transform(it) })
            sortBy { it.completeTime }
        }
}
