package com.devalr.domain.repositories.impl

import android.util.Log
import com.devalr.data.databases.city.CityDao
import com.devalr.data.databases.city.CityEntity
import com.devalr.data.datasources.CityDatasource
import com.devalr.data.dto.CityDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.CityBo
import com.devalr.domain.repositories.CityRepository

class CityRepositoryImpl(
    private val datasource: CityDatasource,
    private val database: CityDao,
    private val citiesMapper: Mapper<List<CityDto>, List<CityBo>>,
    private val citiesDatabaseMapper: Mapper<List<CityBo>, List<CityEntity>>
) : CityRepository {

    override suspend fun fetchCities(): List<CityBo> =
        if (database.isDatabaseFilled()) {
            citiesDatabaseMapper.transformReverse(database.getCities())
        } else {
            val citiesDto = datasource.fetchCities()
            val citiesBo = citiesMapper.transform(citiesDto)
            database.insertCities(citiesDatabaseMapper.transform(citiesBo))
            citiesBo
        }

    override suspend fun activateCity(city: CityBo) {
        database.deactivateCities()
        database.activateCity(city.name)
        Log.d("ALRALR","activating $city")
        val c=database.getActiveCity().firstOrNull()
        Log.d("ALRALR","is active? $c")

    }

    override suspend fun getActiveCity(): CityBo? =
        citiesDatabaseMapper.transformReverse(database.getActiveCity()).firstOrNull()


    override suspend fun selectCity(city: CityBo) =
        database.selectCity(city.name)


    override suspend fun getSelectedCities(): List<CityBo> =
        citiesDatabaseMapper.transformReverse(database.getSelectedCities())

}
