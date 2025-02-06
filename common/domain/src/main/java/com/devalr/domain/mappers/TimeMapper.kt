package com.devalr.domain.mappers

import com.devalr.domain.model.WeatherTime

class TimeMapper : Mapper<String, WeatherTime>() {
    override fun transform(data: String): WeatherTime =
        when (data) {
            "00" -> WeatherTime.PM_12
            "01" -> WeatherTime.AM_1
            "02" -> WeatherTime.AM_2
            "03" -> WeatherTime.AM_3
            "04" -> WeatherTime.AM_4
            "05" -> WeatherTime.AM_5
            "06" -> WeatherTime.AM_6
            "07" -> WeatherTime.AM_7
            "08" -> WeatherTime.AM_8
            "09" -> WeatherTime.AM_9
            "10" -> WeatherTime.AM_10
            "11" -> WeatherTime.AM_11
            "12" -> WeatherTime.AM_12
            "13" -> WeatherTime.PM_1
            "14" -> WeatherTime.PM_2
            "15" -> WeatherTime.PM_3
            "16" -> WeatherTime.PM_4
            "17" -> WeatherTime.PM_5
            "18" -> WeatherTime.PM_6
            "19" -> WeatherTime.PM_7
            "20" -> WeatherTime.PM_8
            "21" -> WeatherTime.PM_9
            "22" -> WeatherTime.PM_10
            "23" -> WeatherTime.PM_11
            else -> WeatherTime.UNKNOWN
        }
}
