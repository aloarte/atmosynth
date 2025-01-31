package com.devalr.dayweather.di

import com.devalr.dayweather.DayWeatherViewModel
import com.devalr.domain.di.domainModules
import org.koin.dsl.module

private val viewModelModules = module {
    factory {
        DayWeatherViewModel(get())
    }
}

val featureDayWeatherModules = module {
    includes(viewModelModules, domainModules)
}
