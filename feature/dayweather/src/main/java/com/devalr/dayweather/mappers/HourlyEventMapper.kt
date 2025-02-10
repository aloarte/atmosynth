package com.devalr.dayweather.mappers

import com.devalr.dayweather.model.HourlyEventVo
import com.devalr.dayweather.model.enums.HourlyEvent
import com.devalr.domain.HourlyEventData
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.SunEvent

class HourlyEventMapper : Mapper<HourlyEventData, HourlyEventVo>() {
    override fun transform(data: HourlyEventData): HourlyEventVo =
        HourlyEventVo(
            hour = "${data.time.hour}:${data.time.minute}",
            completeTime = data.time,
            event =
                when (data.event) {
                    SunEvent.Sunrise -> HourlyEvent.Sunrise
                    SunEvent.Sunset -> HourlyEvent.Sunset
                    SunEvent.Unknown -> HourlyEvent.Unknown
                }
        )
}
