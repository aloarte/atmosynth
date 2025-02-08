package com.devalr.domain.mappers

import com.devalr.domain.SunEventData
import com.devalr.domain.model.SunEvent

class SunEventsMapper : Mapper<Map<String, String>, List<SunEventData>>() {
    override fun transform(data: Map<String, String>): List<SunEventData> =
        data
            .mapNotNull { (key, value) ->
                SunEventData(
                    event =
                        when (key) {
                            "SUNRISE" -> SunEvent.Sunrise
                            "SUNSET" -> SunEvent.Sunset
                            else -> SunEvent.Unknown
                        },
                    time = value
                )
            }.toList()
}
