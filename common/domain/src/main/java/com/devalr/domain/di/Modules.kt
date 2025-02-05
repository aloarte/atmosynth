package com.devalr.domain.di

import com.devalr.data.di.dataModules
import com.devalr.data.dto.DailyWeatherDto
import com.devalr.domain.mappers.DailyWeatherMapper
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.DailyWeatherBo
import com.devalr.domain.repositories.GeminiRepository
import com.devalr.domain.repositories.WeatherRepository
import com.devalr.domain.repositories.impl.GeminiRepositoryImpl
import com.devalr.domain.repositories.impl.WeatherRepositoryImpl
import org.koin.dsl.module

private val repositoriesModules =
    module {
        factory<GeminiRepository> {
            GeminiRepositoryImpl(get())
        }

        factory<WeatherRepository> {
            WeatherRepositoryImpl(get(), get())
        }
    }

private val mapperModules =
    module {
        factory<Mapper<DailyWeatherDto?, DailyWeatherBo?>> {
            DailyWeatherMapper()
        }
    }

val domainModules =
    module {
        includes(mapperModules, repositoriesModules, dataModules)
    }
