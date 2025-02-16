package com.devalr.domain.mappers

import androidx.core.text.isDigitsOnly
import com.devalr.domain.mappers.params.DateMapperParams
import com.devalr.domain.mappers.params.ValueInTimeParams
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.TemperatureRelationBo
import java.time.LocalDateTime

class TemperatureMapper(
    private val dateMapper: Mapper<DateMapperParams, LocalDateTime>,
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<ValueInTimeParams, TemperatureRelationBo>() {
    override fun transform(data: ValueInTimeParams): TemperatureRelationBo {
        val time = timeMapper.transform(data.data.time)
        return TemperatureRelationBo(
            temperature = data.data.value
                .takeIf { it.isDigitsOnly() }
                ?.toInt() ?: 0,
            time = time,
            completeTime = dateMapper.transform(DateMapperParams(data.date, time.text))
        )
    }
}
