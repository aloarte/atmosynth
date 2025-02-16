package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.SkyValueInTimeDto
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.SkyRelationBo

class SkyStateMapper(
    private val skyMapper: Mapper<String, SkyState>,
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<SkyValueInTimeDto, SkyRelationBo>() {
    override fun transform(data: SkyValueInTimeDto): SkyRelationBo =
        SkyRelationBo(
            skyState = skyMapper.transform(data.value),
            time = timeMapper.transform(data.time)
        )
}
