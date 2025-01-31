package com.devalr.domain.di

import com.devalr.data.di.dataModules
import com.devalr.domain.GeminiRepository
import com.devalr.domain.GeminiRepositoryImpl
import org.koin.dsl.module

private val repositoriesModules = module {
    factory<GeminiRepository> {
        GeminiRepositoryImpl(get())
    }
}

val domainModules = module {
    includes(repositoriesModules, dataModules)
}
