package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.hourly.HourlySkyValueInTimeDto
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.common.SkyRelationBo

class SkyStateMapper(
    private val skyMapper: Mapper<String, SkyState>,
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<HourlySkyValueInTimeDto, SkyRelationBo>() {
    override fun transform(data: HourlySkyValueInTimeDto): SkyRelationBo =
        SkyRelationBo(
            skyState = skyMapper.transform(data.value),
            time = timeMapper.transform(data.time)
        )
}
