package com.devalr.data.datasources.impl

import com.devalr.data.datasources.CityDatasource
import com.devalr.data.datasources.helpers.AemetApiHelper
import com.devalr.data.datasources.helpers.Constants.FETCH_CITY_PATH
import com.devalr.data.dto.CityDto
import io.ktor.client.HttpClient

class CityDatasourceImpl(
    private val httpClient: HttpClient,
    private val helper: AemetApiHelper
) : CityDatasource {

    override suspend fun fetchCities(): List<CityDto> {
        val weatherDataUrl = helper.fetchAemetData(
            dataUrl = FETCH_CITY_PATH,
            httpClient = httpClient
        )
        return fetchCitiesData(weatherDataUrl)
    }

    private suspend fun fetchCitiesData(url: String): List<CityDto> =
        helper.runSafely(url, httpClient)
}
