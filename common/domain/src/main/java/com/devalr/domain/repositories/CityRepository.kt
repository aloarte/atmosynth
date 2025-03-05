package com.devalr.domain.repositories

import com.devalr.domain.model.CityBo

interface CityRepository {
    suspend fun fetchCities(): List<CityBo>
    suspend fun selectCity(city: CityBo)
    suspend fun getActiveCity(): CityBo?
    suspend fun activateCity(city: CityBo)
    suspend fun getSelectedCities(): List<CityBo>
}
