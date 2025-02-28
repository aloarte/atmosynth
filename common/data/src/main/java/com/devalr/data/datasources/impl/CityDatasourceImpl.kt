package com.devalr.data.datasources.impl

import com.devalr.data.Secrets
import com.devalr.data.datasources.CityDatasource
import com.devalr.data.dto.CityDto
import com.devalr.data.dto.DataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class CityDatasourceImpl(
    private val httpClient: HttpClient,
    private val secrets: Secrets
) : CityDatasource {

    companion object {
        const val AEMET_HOST = "opendata.aemet.es"
        const val FETCH_HOURLY_WEATHER_PATH =
            "opendata/api/maestro/municipios"
    }

    private suspend fun fetchAemetData(): String {
        val response =
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = AEMET_HOST
                    encodedPath = FETCH_HOURLY_WEATHER_PATH
                }
                header("api_key", secrets.getAemetApiKey())
            }

        return response.body<DataResponse>().requestDataUrl
    }

    private suspend fun fetchCitiesData(url: String): List<CityDto> {
        val response =
            httpClient.get(url) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

        return Json
            .decodeFromString<List<CityDto>>(response.body() as String)
    }

    override suspend fun fetchCities(): List<CityDto> {
        val weatherDataUrl = fetchAemetData()
        return fetchCitiesData(weatherDataUrl)
    }
}
