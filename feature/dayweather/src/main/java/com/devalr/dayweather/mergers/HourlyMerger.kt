package com.devalr.dayweather.mergers

import com.devalr.dayweather.model.HourlyDataVo
import com.devalr.dayweather.model.HourlyEventVo
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.domain.HourlyEventData
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.DailyPredictionBo
import com.devalr.domain.model.weather.HourlyWeatherBo

class HourlyMerger(
    private val eventMapper: Mapper<HourlyEventData, HourlyEventVo>,
    private val hourlyMapper: Mapper<HourlyWeatherBo, HourlyWeatherVo>
) {
    fun merge(dailyPredictionBo: List<DailyPredictionBo>) {
        // TODO: Fetch all data together from the 3 days and then get only the first 24h, calling the method below
        // TODO: This feature may belong to other component. Something like DayHourlyRetirever that calls this merger or something like that.
        dailyPredictionBo.forEach { }
    }

    fun merge(
        hourlyWeatherData: List<HourlyWeatherBo>,
        events: List<HourlyEventData>
    ): List<HourlyDataVo> {
        val eventsVo = events.map { eventMapper.transform(it) }
        val hourlyWeatherDataVo = hourlyWeatherData.map { hourlyMapper.transform(it) }
        val retList =
            mutableListOf<HourlyDataVo>().apply {
                addAll(eventsVo)
                addAll(hourlyWeatherDataVo)
                sortBy { it.hour }
            }
        return retList
    }
}
