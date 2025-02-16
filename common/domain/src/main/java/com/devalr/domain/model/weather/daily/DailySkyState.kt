package com.devalr.domain.model.weather.daily

import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.SkyState

data class DailySkyState(val state: SkyState,val description:String, val time: DayTime)
