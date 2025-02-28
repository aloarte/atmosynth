package com.devalr.data.datasources

import com.devalr.data.dto.CityDto

interface CityDatasource {
    suspend fun fetchCities(): List<CityDto>
}