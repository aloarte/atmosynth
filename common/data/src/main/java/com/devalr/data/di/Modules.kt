package com.devalr.data.di

import com.devalr.data.GeminiDatasource
import com.devalr.data.GeminiDatasourceImpl
import com.google.ai.client.generativeai.GenerativeModel
import org.koin.dsl.module

private val dataSourcesModule = module {
    factory<GeminiDatasource> {
        GeminiDatasourceImpl(get())
    }
}

private val dataFrameworkModule = module {
    single<GenerativeModel> {
        GenerativeModel(
            modelName = "gemini-1.5-flash",
            apiKey = ""
        )
    }
}

val dataModules = module {
    includes(dataSourcesModule, dataFrameworkModule)
}
