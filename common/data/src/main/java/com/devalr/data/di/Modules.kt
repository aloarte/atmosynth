package com.devalr.data.di

import android.app.Application
import androidx.room.Room
import com.devalr.data.Secrets
import com.devalr.data.databases.city.CityDao
import com.devalr.data.databases.city.CityDatabase
import com.devalr.data.databases.prompt.PromptResultDao
import com.devalr.data.databases.prompt.PromptResultDatabase
import com.devalr.data.datasources.CityDatasource
import com.devalr.data.datasources.GeminiDatasource
import com.devalr.data.datasources.WeatherDatasource
import com.devalr.data.datasources.helpers.AemetApiHelper
import com.devalr.data.datasources.impl.CityDatasourceImpl
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
import org.koin.android.ext.koin.androidApplication
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
        factory<CityDatasource> {
            CityDatasourceImpl(get(), get())
        }
    }

private val databaseModule = module {
    single { providePromptDataBase(androidApplication()) }
    single { provideCitiesDataBase(androidApplication()) }
    single { providePromptDao(get()) }
    single { provideCitiesDao(get()) }
}

private fun providePromptDataBase(application: Application): PromptResultDatabase =
    Room.databaseBuilder(
        application,
        PromptResultDatabase::class.java,
        "PromptResultDatabase"
    )
        .fallbackToDestructiveMigration()
        .build()

private fun provideCitiesDataBase(application: Application): CityDatabase =
    Room.databaseBuilder(
        application,
        CityDatabase::class.java,
        "CitiesDatabase"
    )
        .fallbackToDestructiveMigration()
        .build()

private fun providePromptDao(dataBase: PromptResultDatabase): PromptResultDao =
    dataBase.promptResultDao()

private fun provideCitiesDao(dataBase: CityDatabase): CityDao =
    dataBase.cityDao()

private val dataFrameworkModule =
    module {


        single { AemetApiHelper(get()) }
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
                        }
                    )
                }
                install(Logging) {
                    level = LogLevel.ALL
                    logger = Logger.ANDROID
                }
                install(HttpTimeout) {
                    requestTimeoutMillis = 20_000
                    connectTimeoutMillis = 10_000
                    socketTimeoutMillis = 30_000
                }
            }
        }
    }

val dataModules =
    module {
        includes(databaseModule, dataSourcesModule, dataFrameworkModule)
    }
