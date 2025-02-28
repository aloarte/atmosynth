package com.devalr.domain.mappers.city

import com.devalr.data.dto.CityDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.CityBo
import com.devalr.domain.model.GeographicDataBo

class CitiesMapper : Mapper<List<CityDto>, List<CityBo>>() {
    override fun transform(data: List<CityDto>): List<CityBo> {
        return data.map {
            CityBo(
                id = it.id,
                population = it.population,
                name = it.name,
                geographicData = GeographicDataBo(
                    altitude = it.altitude,
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            )
        }
    }
}