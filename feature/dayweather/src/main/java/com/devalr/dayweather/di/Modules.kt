package com.devalr.dayweather.di

import com.devalr.dayweather.DayWeatherViewModel
import com.devalr.dayweather.mappers.HourlyEventMapper
import com.devalr.dayweather.mappers.HourlyWeatherMapper
import com.devalr.dayweather.mappers.SkyEnumMapper
import com.devalr.dayweather.mergers.HourlyMerger
import com.devalr.dayweather.model.HourlyEventVo
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.dayweather.model.SkyStateIcon
import com.devalr.domain.HourlyEventData
import com.devalr.domain.di.domainModules
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.SkyState
import com.devalr.domain.model.weather.HourlyWeatherBo
import org.koin.core.qualifier.named
import org.koin.dsl.module

private val viewModelModules =
    module {
        factory {
            DayWeatherViewModel(get(), get(), get())
        }
    }

private val mapperModules =
    module {
        factory {
            HourlyMerger(
                get(named("HourlyEventMapper")),
                get(named("HourlyWeatherMapper"))
            )
        }

        factory<Mapper<HourlyEventData, HourlyEventVo>>(named("HourlyEventMapper")) {
            HourlyEventMapper()
        }

        factory<Mapper<HourlyWeatherBo, HourlyWeatherVo>>(named("HourlyWeatherMapper")) {
            HourlyWeatherMapper(get(named("SkyEnumMapper")))
        }

        factory<Mapper<SkyState, SkyStateIcon>>(named("SkyEnumMapper")) {
            SkyEnumMapper()
        }

    }

val featureDayWeatherModules =
    module {
        includes(mapperModules, viewModelModules, domainModules)
    }
