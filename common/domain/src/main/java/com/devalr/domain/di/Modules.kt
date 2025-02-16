package com.devalr.domain.di

import com.devalr.data.di.dataModules
import com.devalr.data.dto.dailyweather.hourly.HourlyWeatherDto
import com.devalr.data.dto.dailyweather.hourly.HourlyDto
import com.devalr.data.dto.dailyweather.hourly.HourlySkyValueInTimeDto
import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto
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
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.hourly.HourlyPredictionBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherDataBo
import com.devalr.domain.model.weather.common.HumidityRelationBo
import com.devalr.domain.model.weather.common.RainRelationBo
import com.devalr.domain.model.weather.common.SkyRelationBo
import com.devalr.domain.model.weather.common.SnowRelationBo
import com.devalr.domain.model.weather.common.TemperatureRelationBo
import com.devalr.domain.model.weather.common.ThermalRelationBo
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
        factory<Mapper<HourlyWeatherDto?, HourlyWeatherDataBo?>>(named("DailyWeatherMapper")) {
            DailyWeatherMapper(get(named("DayMapper")))
        }
        factory<Mapper<HourlyDto, HourlyPredictionBo>>(named("DayMapper")) {
            DayMapper(
                get(named("DateMapper")),
                get(named("HumidityMapper")),
                get(named("RainMapper")),
                get(named("SkyStateMapper")),
                get(named("SnowMapper")),
                get(named("TemperatureMapper")),
                get(named("ThermalMapper")),
                get()
            )
        }

        factory<Mapper<HourlyValueInTimeDto, HumidityRelationBo>>(named("HumidityMapper")) {
            HumidityMapper(
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<HourlyValueInTimeDto, RainRelationBo>>(named("RainMapper")) {
            RainMapper(
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<HourlySkyValueInTimeDto, SkyRelationBo>>(named("SkyStateMapper")) {
            SkyStateMapper(
                get(named("SkyMapper")),
                get(named("TimeEnumMapper"))
            )
        }

        factory<Mapper<HourlyValueInTimeDto, SnowRelationBo>>(named("SnowMapper")) {
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

        factory<Mapper<HourlyValueInTimeDto, ThermalRelationBo>>(named("ThermalMapper")) {
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

        factory<Mapper<String, SkyState>>(named("SkyMapper")) {
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
