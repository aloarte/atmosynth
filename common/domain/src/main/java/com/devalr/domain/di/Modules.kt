package com.devalr.domain.di

import com.devalr.data.di.dataModules
import com.devalr.data.dto.dailyweather.DailyWeatherDto
import com.devalr.data.dto.dailyweather.DayDto
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.mappers.DailyWeatherMapper
import com.devalr.domain.mappers.DayMapper
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.mappers.RainMapper
import com.devalr.domain.mappers.TemperatureMapper
import com.devalr.domain.mappers.TimeMapper
import com.devalr.domain.mergers.DayMerger
import com.devalr.domain.model.WeatherTime
import com.devalr.domain.model.weather.DailyPredictionBo
import com.devalr.domain.model.weather.DailyWeatherBo
import com.devalr.domain.model.weather.RainRelationBo
import com.devalr.domain.model.weather.TemperatureRelationBo
import com.devalr.domain.repositories.GeminiRepository
import com.devalr.domain.repositories.WeatherRepository
import com.devalr.domain.repositories.impl.GeminiRepositoryImpl
import com.devalr.domain.repositories.impl.WeatherRepositoryImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val repositoriesModules =
    module {
        factory<GeminiRepository> {
            GeminiRepositoryImpl(get())
        }

        factory<WeatherRepository> {
            WeatherRepositoryImpl(get(), get(named("DailyWeatherMapper")))
        }
    }

private val mapperModules =
    module {
        factory<Mapper<DailyWeatherDto?, DailyWeatherBo?>>(named("DailyWeatherMapper")) {
            DailyWeatherMapper(get(named("DayMapper")))
        }
        factory<Mapper<DayDto, DailyPredictionBo>>(named("DayMapper")) {
            DayMapper(get(named("TemperatureMapper")), get(named("RainMapper")), get())
        }

        factory<Mapper<ValueInTimeDto, TemperatureRelationBo>>(named("TemperatureMapper")) {
            TemperatureMapper(get(named("TimeMapper")))
        }

        factory<Mapper<ValueInTimeDto, RainRelationBo>>(named("RainMapper")) {
            RainMapper(get(named("TimeMapper")))
        }

        factory<Mapper<String, WeatherTime>>(named("TimeMapper")) {
            TimeMapper()
        }
    }

private val mergerModules =
    module {
        factory {
            DayMerger()
        }
    }

val domainModules =
    module {
        includes(mergerModules, mapperModules, repositoriesModules, dataModules)
    }
