package com.devalr.domain.mappers.daily

import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime

class DayTimeIntStateMapper : Mapper<Int, DayTime>() {
    override fun transform(data: Int): DayTime = when (data) {
        6 -> DayTime.EarlyMorning
        12 -> DayTime.Morning
        18 -> DayTime.Afternoon
        24 -> DayTime.Night
        else -> DayTime.Unknown
    }
}
