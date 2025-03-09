package com.devalr.cityselector.di

import com.devalr.cityselector.CityViewModel
import com.devalr.domain.di.domainModules
import org.koin.dsl.module

private val viewModelModules = module {
    factory {
        CityViewModel(get(), get())
    }
}

val featureCitySelectorModules = module {
    includes(domainModules, viewModelModules)
}
