package com.devalr.data.di

import com.devalr.data.Secrets
import com.devalr.data.datasources.GeminiDatasource
import com.devalr.data.datasources.WeatherDatasource
import com.devalr.data.datasources.impl.GeminiDatasourceImpl
import com.devalr.data.datasources.impl.WeatherDatasourceImpl
import com.google.ai.client.generativeai.GenerativeModel
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val dataSourcesModule =
    module {
        factory<GeminiDatasource> {
            GeminiDatasourceImpl(get())
        }
        factory<WeatherDatasource> {
            WeatherDatasourceImpl(get(), get())
        }
    }

private val dataFrameworkModule =
    module {

        single { Secrets(context = get(named("AppContext"))) }

        single<GenerativeModel> {
            GenerativeModel(
                modelName = "gemini-1.5-flash",
                apiKey = get<Secrets>().getGeminiApiKey(),
            )
        }
        single<HttpClient> {

            HttpClient(engine = CIO.create()) {
                install(ContentNegotiation) {
                    json(
                        Json {
                            ignoreUnknownKeys = true
                            isLenient = true
                        },
                    )
                }
                install(Logging) {
                    level = LogLevel.ALL
                    logger = Logger.ANDROID
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = 20_000
                    connectTimeoutMillis = 10_000
                    socketTimeoutMillis = 5_000
                }
            }
        }
    }

val dataModules =
    module {
        includes(dataSourcesModule, dataFrameworkModule)
    }
