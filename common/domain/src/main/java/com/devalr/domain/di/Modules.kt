package com.devalr.domain.di

import com.devalr.data.di.dataModules
import com.devalr.data.dto.dailyweather.DailyWeatherDto
import com.devalr.data.dto.dailyweather.DayDto
import com.devalr.data.dto.dailyweather.SkyValueInTimeDto
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.mappers.DailyWeatherMapper
import com.devalr.domain.mappers.DayMapper
import com.devalr.domain.mappers.HumidityMapper
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.mappers.RainMapper
import com.devalr.domain.mappers.SkyMapper
import com.devalr.domain.mappers.SkyStateMapper
import com.devalr.domain.mappers.SnowMapper
import com.devalr.domain.mappers.TemperatureMapper
import com.devalr.domain.mappers.ThermalMapper
import com.devalr.domain.mappers.TimeMapper
import com.devalr.domain.mergers.DayMerger
import com.devalr.domain.model.SkyState
import com.devalr.domain.model.WeatherTime
import com.devalr.domain.model.weather.DailyPredictionBo
import com.devalr.domain.model.weather.DailyWeatherBo
import com.devalr.domain.model.weather.HumidityRelationBo
import com.devalr.domain.model.weather.RainRelationBo
import com.devalr.domain.model.weather.SkyRelationBo
import com.devalr.domain.model.weather.SnowRelationBo
import com.devalr.domain.model.weather.TemperatureRelationBo
import com.devalr.domain.model.weather.ThermalRelationBo
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
            DayMapper(
                get(named("HumidityMapper")),
                get(named("RainMapper")),
                get(named("SkyMapper")),
                get(named("SnowMapper")),
                get(named("TemperatureMapper")),
                get(named("ThermalMapper")),
                get()
            )
        }

        factory<Mapper<ValueInTimeDto, HumidityRelationBo>>(named("HumidityMapper")) {
            HumidityMapper(get(named("TimeEnumMapper")))
        }

        factory<Mapper<ValueInTimeDto, RainRelationBo>>(named("RainMapper")) {
            RainMapper(get(named("TimeEnumMapper")))
        }

        factory<Mapper<SkyValueInTimeDto, SkyRelationBo>>(named("SkyMapper")) {
            SkyStateMapper(get(named("TimeEnumMapper")), get(named("SkyEnumMapper")))
        }

        factory<Mapper<ValueInTimeDto, SnowRelationBo>>(named("SnowMapper")) {
            SnowMapper(get(named("TimeEnumMapper")))
        }

        factory<Mapper<ValueInTimeDto, TemperatureRelationBo>>(named("TemperatureMapper")) {
            TemperatureMapper(get(named("TimeEnumMapper")))
        }

        factory<Mapper<ValueInTimeDto, ThermalRelationBo>>(named("ThermalMapper")) {
            ThermalMapper(get(named("TimeEnumMapper")))
        }

        factory<Mapper<String, SkyState>>(named("SkyEnumMapper")) {
            SkyMapper()
        }

        factory<Mapper<String, WeatherTime>>(named("TimeEnumMapper")) {
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
