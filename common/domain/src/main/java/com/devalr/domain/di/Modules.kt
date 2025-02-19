package com.devalr.domain.di

import com.devalr.data.di.dataModules
import com.devalr.data.dto.dailyweather.daily.DailyDto
import com.devalr.data.dto.dailyweather.daily.DailyMaxMinValuesDto
import com.devalr.data.dto.dailyweather.daily.DailySkyValueInTimeDto
import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeIntDto
import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeStrDto
import com.devalr.data.dto.dailyweather.daily.DailyWeatherDto
import com.devalr.data.dto.dailyweather.daily.DailyWindInTimeDto
import com.devalr.data.dto.dailyweather.daily.IntValueInTimeDto
import com.devalr.data.dto.dailyweather.hourly.HourlyDto
import com.devalr.data.dto.dailyweather.hourly.HourlySkyValueInTimeDto
import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto
import com.devalr.data.dto.dailyweather.hourly.HourlyWeatherDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.mappers.daily.DailyMapper
import com.devalr.domain.mappers.daily.DailyStateMapper
import com.devalr.domain.mappers.daily.DailyValuesInTimeMapper
import com.devalr.domain.mappers.daily.DailyWeatherMapper
import com.devalr.domain.mappers.daily.DayTimeIntStateMapper
import com.devalr.domain.mappers.daily.DayTimeStringStateMapper
import com.devalr.domain.mappers.daily.MinMaxValueMapper
import com.devalr.domain.mappers.daily.RainPrecipitationMapper
import com.devalr.domain.mappers.daily.SnowPrecipitationMapper
import com.devalr.domain.mappers.daily.WindDirectionMapper
import com.devalr.domain.mappers.daily.WindStateMapper
import com.devalr.domain.mappers.hourly.DateMapper
import com.devalr.domain.mappers.hourly.HourlyMapper
import com.devalr.domain.mappers.hourly.HourlyWeatherMapper
import com.devalr.domain.mappers.hourly.HumidityMapper
import com.devalr.domain.mappers.hourly.RainMapper
import com.devalr.domain.mappers.hourly.SkyMapper
import com.devalr.domain.mappers.hourly.SkyStateMapper
import com.devalr.domain.mappers.hourly.SnowMapper
import com.devalr.domain.mappers.hourly.TemperatureMapper
import com.devalr.domain.mappers.hourly.ThermalMapper
import com.devalr.domain.mappers.hourly.TimeMapper
import com.devalr.domain.mappers.params.DateMapperParams
import com.devalr.domain.mappers.params.ValueInTimeParams
import com.devalr.domain.mergers.DayMerger
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.enums.WindDirection
import com.devalr.domain.model.weather.common.HumidityRelationBo
import com.devalr.domain.model.weather.common.RainRelationBo
import com.devalr.domain.model.weather.common.SkyRelationBo
import com.devalr.domain.model.weather.common.SnowRelationBo
import com.devalr.domain.model.weather.common.TemperatureRelationBo
import com.devalr.domain.model.weather.common.ThermalRelationBo
import com.devalr.domain.model.weather.daily.DailySkyState
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import com.devalr.domain.model.weather.daily.DailyWeatherDataBo
import com.devalr.domain.model.weather.daily.DailyWindState
import com.devalr.domain.model.weather.daily.MaxMinValueBo
import com.devalr.domain.model.weather.daily.PrecipitationProbability
import com.devalr.domain.model.weather.daily.ValuesDayTimeBo
import com.devalr.domain.model.weather.hourly.HourlyPredictionBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherDataBo
import com.devalr.domain.repositories.GeminiRepository
import com.devalr.domain.repositories.WeatherRepository
import com.devalr.domain.repositories.impl.GeminiRepositoryImpl
import com.devalr.domain.repositories.impl.WeatherRepositoryImpl
import com.devalr.domain.usecases.WeatherUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.time.LocalDateTime

private val repositoriesModules =
    module {
        factory<GeminiRepository> {
            GeminiRepositoryImpl(get(),get())
        }

        factory<WeatherRepository> {
            WeatherRepositoryImpl(
                get(),
                get(named("HourlyWeatherMapper")),
                get(named("DailyWeatherMapper"))
            )
        }
    }

private val usecasesModules = module {
    factory {
        WeatherUseCase(get())
    }
}

private val mapperModules =
    module {
        factory<Mapper<DailyWeatherDto?, DailyWeatherDataBo?>>(named("DailyWeatherMapper")) {
            DailyWeatherMapper(get(named("DailyMapper")))
        }

        factory<Mapper<DailyDto, DailyWeatherBo>>(named("DailyMapper")) {
            DailyMapper(
                get(named("RainPrecipitationMapper")),
                get(named("SnowPrecipitationMapper")),
                get(named("WindStateMapper")),
                get(named("DailyStateMapper")),
                get(named("MinMaxValueMapper"))
            )
        }

        factory<Mapper<DailyValueInTimeIntDto, PrecipitationProbability>>(named("RainPrecipitationMapper")) {
            RainPrecipitationMapper(get(named("DayTimeStringStateMapper")))
        }

        factory<Mapper<DailyValueInTimeStrDto, PrecipitationProbability>>(named("SnowPrecipitationMapper")) {
            SnowPrecipitationMapper(get(named("DayTimeStringStateMapper")))
        }

        factory<Mapper<DailyWindInTimeDto, DailyWindState>>(named("WindStateMapper")) {
            WindStateMapper(
                get(named("WindDirectionMapper")),
                get(named("DayTimeStringStateMapper"))
            )
        }

        factory<Mapper<DailySkyValueInTimeDto, DailySkyState>>(named("DailyStateMapper")) {
            DailyStateMapper(
                get(named("DayTimeStringStateMapper")),
                get(named("SkyMapper"))
            )
        }

        factory<Mapper<DailyMaxMinValuesDto, MaxMinValueBo>>(named("MinMaxValueMapper")) {
            MinMaxValueMapper(get(named("DailyValuesInTimeMapper")))
        }

        factory<Mapper<IntValueInTimeDto, ValuesDayTimeBo>>(named("DailyValuesInTimeMapper")) {
            DailyValuesInTimeMapper(get(named("DayTimeIntStateMapper")))
        }

        factory<Mapper<String, WindDirection>>(named("WindDirectionMapper")) {
            WindDirectionMapper()
        }

        factory<Mapper<String, DayTime>>(named("DayTimeStringStateMapper")) {
            DayTimeStringStateMapper()
        }

        factory<Mapper<Int, DayTime>>(named("DayTimeIntStateMapper")) {
            DayTimeIntStateMapper()
        }













        factory<Mapper<HourlyWeatherDto?, HourlyWeatherDataBo?>>(named("HourlyWeatherMapper")) {
            HourlyWeatherMapper(get(named("HourlyMapper")))
        }

        factory<Mapper<HourlyDto, HourlyPredictionBo>>(named("HourlyMapper")) {
            HourlyMapper(
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
        includes(mergerModules, mapperModules, usecasesModules, repositoriesModules, dataModules)
    }
