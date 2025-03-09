package com.devalr.data.datasources.helpers

import com.devalr.data.Secrets
import com.devalr.data.datasources.helpers.Constants.AEMET_HOST
import com.devalr.data.datasources.helpers.Constants.API_KEY_HEADER_NAME
import com.devalr.data.dto.DataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import kotlinx.coroutines.delay
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.EOFException

class AemetApiHelper(private val secrets: Secrets) {

    suspend fun fetchAemetData(
        dataUrl: String,
        httpClient: HttpClient,
    ): String =
        httpClient.get {
            url {
                protocol = URLProtocol.HTTPS
                host = AEMET_HOST
                encodedPath = dataUrl
            }
            header(API_KEY_HEADER_NAME, secrets.getAemetApiKey())
        }.body<DataResponse>().requestDataUrl

    suspend inline fun <reified T> runSafely(
        url: String,
        httpClient: HttpClient,
        retries: Int = 0
    ): T {
        var currentRetries = retries
        while (currentRetries < 5) {
            try {
                val response: HttpResponse = httpClient.get(url) {
                    header(HttpHeaders.ContentType, ContentType.Application.Json)
                }
                return Json.decodeFromString<T>(response.body() as String)
            } catch (exception: SocketTimeoutException) {
                currentRetries++
                delay(1000L + currentRetries * 300L)
                if (currentRetries >= 5) {
                    throw Exception("Max retries reached due to SocketTimeoutException")
                }
            } catch (exception: HttpRequestTimeoutException) {
                currentRetries++
                delay(1000L + currentRetries * 300L)
                if (currentRetries >= 5) {
                    throw Exception("Max retries reached due to HttpRequestTimeoutException")
                }
            } catch (exception: SerializationException) {
                currentRetries++
                delay(1000L + currentRetries * 300L)
                if (currentRetries >= 5) {
                    throw Exception("Max retries reached due to SerializationException")
                }
            } catch (exception: EOFException) {
                currentRetries++
                delay(1000L + currentRetries * 300L)
                if (currentRetries >= 5) {
                    throw Exception("Max retries reached due to EOFException")
                }
            } catch (exception: Exception) {
                throw exception
            }
        }
        throw Exception("Unexpected error on run safely")
    }
}
