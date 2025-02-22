package com.devalr.dayweather

import com.devalr.domain.model.weather.hourly.HourlyWeatherBo
import java.time.LocalDateTime
import java.time.ZoneId

fun String.processForAI() = this
    .replace("PrecipitationProbability", "")
    .replace("DailySkyState", "")
    .replace("DailyWindState", "")
    .replace("ValuesDayTimeBo", "")
    .replace("MaxMinValueBo", "")


fun List<HourlyWeatherBo>.findClosestDate() = with(LocalDateTime.now(ZoneId.of("Europe/Madrid"))) {
    this@findClosestDate.minByOrNull {
        kotlin.math.abs(it.completeTime.minute - minute).toLong() +
                kotlin.math.abs(it.completeTime.hour - hour).toLong() * 60
    }
}