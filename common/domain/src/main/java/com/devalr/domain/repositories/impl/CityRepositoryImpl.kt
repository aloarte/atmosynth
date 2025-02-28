package com.devalr.domain.repositories.impl

import com.devalr.data.datasources.CityDatasource
import com.devalr.data.dto.CityDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.CityBo
import com.devalr.domain.repositories.CityRepository

class CityRepositoryImpl(
    private val datasource: CityDatasource,
    private val citiesMapper: Mapper<List<CityDto>, List<CityBo>>
) : CityRepository {

    override suspend fun fetchCities(): List<CityBo> {
        val citiesDto = datasource.fetchCities()
        return citiesMapper.transform(citiesDto)
    }

    override suspend fun selectCity(city: CityBo) {
        TODO("Not yet implemented")
    }

    override suspend fun getSelectedCities(): List<CityBo> {
        TODO("Not yet implemented")
    }
}
