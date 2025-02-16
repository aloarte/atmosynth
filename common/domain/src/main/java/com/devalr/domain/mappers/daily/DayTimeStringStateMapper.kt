package com.devalr.domain.mappers.daily

import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime

class DayTimeStringStateMapper : Mapper<String, DayTime>() {
    override fun transform(data: String): DayTime = when (data) {
        "00-06" -> DayTime.EarlyMorning
        "06-12" -> DayTime.Morning
        "12-18" -> DayTime.Afternoon
        "18-24" -> DayTime.Night
        else -> DayTime.Unknown
    }
}
