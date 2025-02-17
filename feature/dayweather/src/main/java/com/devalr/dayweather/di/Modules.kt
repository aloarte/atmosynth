package com.devalr.dayweather.di

import com.devalr.dayweather.DayWeatherViewModel
import com.devalr.dayweather.mappers.HourlyEventMapper
import com.devalr.dayweather.mappers.HourlyWeatherMapper
import com.devalr.dayweather.mappers.NowWeatherMapper
import com.devalr.dayweather.mappers.SkyEnumMapper
import com.devalr.dayweather.mergers.HourlyMerger
import com.devalr.dayweather.model.enums.SkyStateIcon
import com.devalr.dayweather.model.hourly.HourlyEventVo
import com.devalr.dayweather.model.hourly.HourlyWeatherVo
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.domain.HourlyEventData
import com.devalr.domain.di.domainModules
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherBo
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val viewModelModules =
    module {
        factory {
            DayWeatherViewModel(get(), get(), get(), get(named("NowWeatherMapper")))
        }
    }

private val mapperModules =
    module {
        factory {
            HourlyMerger(
                get(named("VoHourlyEventMapper")),
                get(named("VoHourlyWeatherMapper"))
            )
        }

        factory<Mapper<HourlyEventData, HourlyEventVo>>(named("VoHourlyEventMapper")) {
            HourlyEventMapper()
        }

        factory<Mapper<HourlyWeatherBo, HourlyWeatherVo>>(named("VoHourlyWeatherMapper")) {
            HourlyWeatherMapper(get(named("VoSkyEnumMapper")))
        }

        factory<Mapper<DailyWeatherBo, NowWeatherDataVo>>(named("NowWeatherMapper")) {
            NowWeatherMapper(get(named("VoSkyEnumMapper")))
        }

        factory<Mapper<SkyState, SkyStateIcon>>(named("VoSkyEnumMapper")) {
            SkyEnumMapper()
        }

    }

val featureDayWeatherModules =
    module {
        includes(mapperModules, viewModelModules, domainModules)
    }
