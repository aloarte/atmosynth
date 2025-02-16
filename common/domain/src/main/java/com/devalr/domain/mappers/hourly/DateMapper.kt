package com.devalr.domain.mappers.hourly

import com.devalr.domain.mappers.Mapper
import com.devalr.domain.mappers.params.DateMapperParams
import java.time.LocalDateTime
import java.time.LocalTime

class DateMapper : Mapper<DateMapperParams, LocalDateTime>() {
    override fun transform(data: DateMapperParams): LocalDateTime {
        val dateTime = LocalDateTime.parse(data.date)
        val hourTime = LocalTime.parse(data.hour)
        return dateTime.withHour(hourTime.hour).withMinute(hourTime.minute)
    }
}
