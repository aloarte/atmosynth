package com.devalr.dayweather

import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.hourly.HourlyWeatherVo
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import com.devalr.domain.model.weather.daily.DailyWeatherDataBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherBo
import java.time.LocalDateTime
import java.time.ZoneId


fun DailyWeatherDataBo.getPromptForNowWeatherData() = predictions
    .first()
    .toString()
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

fun List<HourlyDataVo>.getPromptForPrecipitationByHours() =
    filterIsInstance<HourlyWeatherVo>().joinToString(",") {
        StringBuilder()
            .append("{")
            .append(" SkyState: ${it.skyState}")
            .append(" Time: ${it.hour}")
            .append("}").toString()
    }


fun DailyWeatherBo.getPromptForTodayPrecipitations() =
    StringBuilder()
        .append("{ RainProbabilities: ${rainProbabilities},")
        .append(" SnowProbabilities: ${snowProbabilities},")
        .append(" SkyStates: ${skyStates}} ,")
        .toString()


