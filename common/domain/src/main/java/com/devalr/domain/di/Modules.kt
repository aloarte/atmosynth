package com.devalr.domain.di

import com.devalr.data.di.dataModules
import com.devalr.data.dto.dailyweather.DailyWeatherDto
import com.devalr.data.dto.dailyweather.DayDto
import com.devalr.data.dto.dailyweather.SkyValueInTimeDto
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.mappers.DailyWeatherMapper
import com.devalr.domain.mappers.DateMapper
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
import com.devalr.domain.mappers.params.DateMapperParams
import com.devalr.domain.mappers.params.ValueInTimeParams
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
import java.time.LocalDateTime

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
                get(named("DateMapper")),
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
            HumidityMapper(
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<ValueInTimeDto, RainRelationBo>>(named("RainMapper")) {
            RainMapper(
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<SkyValueInTimeDto, SkyRelationBo>>(named("SkyMapper")) {
            SkyStateMapper(
                get(named("SkyEnumMapper")),
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<ValueInTimeDto, SnowRelationBo>>(named("SnowMapper")) {
            SnowMapper(
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<ValueInTimeParams, TemperatureRelationBo>>(named("TemperatureMapper")) {
            TemperatureMapper(
                get(named("DateMapper")),
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<ValueInTimeDto, ThermalRelationBo>>(named("ThermalMapper")) {
            ThermalMapper(
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<DateMapperParams, LocalDateTime>>(named("DateMapper")) {
            DateMapper()
        }

        factory<Mapper<String, WeatherTime>>(named("TimeEnumMapper")) {
            TimeMapper()
        }

        factory<Mapper<String, SkyState>>(named("SkyEnumMapper")) {
            SkyMapper()
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
