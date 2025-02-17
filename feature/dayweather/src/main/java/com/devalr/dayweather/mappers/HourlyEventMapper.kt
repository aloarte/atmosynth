package com.devalr.dayweather.mappers

import com.devalr.dayweather.model.hourly.HourlyEventVo
import com.devalr.dayweather.model.enums.HourlyEvent
import com.devalr.domain.HourlyEventData
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.SunEvent

class HourlyEventMapper : Mapper<HourlyEventData, HourlyEventVo>() {
    override fun transform(data: HourlyEventData): HourlyEventVo =
        HourlyEventVo(
            hour = "${data.time.hour.toString().padStart(2, '0')}:${
                data.time.minute.toString().padStart(2, '0')
            }",
            completeTime = data.time,
            event = when (data.event) {
                SunEvent.Sunrise -> HourlyEvent.Sunrise
                SunEvent.Sunset -> HourlyEvent.Sunset
                SunEvent.Unknown -> HourlyEvent.Unknown
            }
        )
}
