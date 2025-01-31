package com.devalr.cityselector.di

import com.devalr.domain.di.domainModules
import org.koin.dsl.module

private val viewModelModules = module {
    factory { }
}

val featureCitySelectorModules = module {
    includes(domainModules, viewModelModules)
}
