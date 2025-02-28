package com.devalr.domain.mappers.city

import com.devalr.data.databases.city.CityEntity
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.CityBo

class CitiesDatabaseMapper : Mapper<List<CityBo>, List<CityEntity>>() {
    override fun transform(data: List<CityBo>): List<CityEntity> = data.map {
        CityEntity(
            cityId = it.id,
            name = it.name,
            population = it.population
        )
    }

    override fun transformReverse(data: List<CityEntity>): List<CityBo> = data.map {
        CityBo(
            id = it.cityId,
            name = it.name,
            population = it.population
        )
    }

}