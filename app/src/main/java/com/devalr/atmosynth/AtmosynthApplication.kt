package com.devalr.atmosynth

import android.app.Application
import com.devalr.cityselector.di.featureCitySelectorModules
import com.devalr.dayweather.di.featureDayWeatherModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AtmosynthApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@AtmosynthApplication)
            // Load modules
            modules(featureCitySelectorModules, featureDayWeatherModules)

        }
    }
}