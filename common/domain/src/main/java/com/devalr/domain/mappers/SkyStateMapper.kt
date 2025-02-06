package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.SkyValueInTimeDto
import com.devalr.domain.model.SkyState
import com.devalr.domain.model.WeatherTime
import com.devalr.domain.model.weather.SkyRelationBo

class SkyStateMapper(
    private val timeMapper: Mapper<String, WeatherTime>,
    private val skyMapper: Mapper<String, SkyState>,
) : Mapper<SkyValueInTimeDto, SkyRelationBo>() {
    override fun transform(data: SkyValueInTimeDto): SkyRelationBo =
        SkyRelationBo(
            skyState = skyMapper.transform(data.value),
            time = timeMapper.transform(data.time),
        )
}
