package com.devalr.domain.model.weather.daily

import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.WindDirection

data class DailyWindState(val direction: WindDirection, val speed: Int, val time: DayTime)