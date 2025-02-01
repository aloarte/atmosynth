package com.devalr.domain.di

import com.devalr.data.di.dataModules
import com.devalr.domain.repositories.GeminiRepository
import com.devalr.domain.repositories.WeatherRepository
import com.devalr.domain.repositories.impl.GeminiRepositoryImpl
import com.devalr.domain.repositories.impl.WeatherRepositoryImpl
import org.koin.dsl.module

private val repositoriesModules =
    module {
        factory<GeminiRepository> {
            GeminiRepositoryImpl(get())
        }

        factory<WeatherRepository> {
            WeatherRepositoryImpl(get())
        }
    }

val domainModules =
    module {
        includes(repositoriesModules, dataModules)
    }
