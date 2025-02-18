package com.devalr.dayweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalr.dayweather.extensions.toCelsius
import com.devalr.dayweather.interactions.Event
import com.devalr.dayweather.interactions.Event.ChangeCity
import com.devalr.dayweather.interactions.Event.LoadScreen
import com.devalr.dayweather.interactions.Event.OnUploadErrorState
import com.devalr.dayweather.interactions.Event.OnUploadLoadingState
import com.devalr.dayweather.interactions.State
import com.devalr.dayweather.mergers.HourlyMerger
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherBo
import com.devalr.domain.repositories.GeminiRepository
import com.devalr.domain.usecases.Result
import com.devalr.domain.usecases.WeatherUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId

class DayWeatherViewModel(
    private val geminiRepository: GeminiRepository,
    private val weatherUseCase: WeatherUseCase,
    private val hourlyMerger: HourlyMerger,
    private val nowWeatherMapper: Mapper<DailyWeatherBo, NowWeatherDataVo>

) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state = _state
        .onStart { handleEvent(LoadScreen) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    fun handleEvent(event: Event) {
        when (event) {
            is OnUploadErrorState -> updateErrorState(event.error)
            is OnUploadLoadingState -> updateLoadingState(event.loading)
            ChangeCity -> TODO()
            LoadScreen -> loadData()

        }
    }

    private fun updateErrorState(error: Boolean) {
        _state.update { currentState ->
            currentState.copy(errorReceived = error)
        }
    }

    private fun updateLoadingState(loading: Boolean) {
        _state.update { currentState ->
            currentState.copy(loadingWeather = loading)
        }
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            handleEvent(OnUploadLoadingState(true))

            when (val weatherResult =
                weatherUseCase.execute(viewModelScope, WeatherUseCase.Params("13034"))) {
                is Result.Error -> {
                    handleEvent(OnUploadLoadingState(false))
                }

                is Result.Success -> {
                    val hourlyNow =
                        weatherResult.data.hourlyData.predictions.first().hourlyData.findClosestDate()
                    _state.update { currentState ->
                        currentState.copy(
                            weatherByHours = hourlyMerger.merge(
                                weatherResult.data.hourlyData.predictions,
                                2,
                                24
                            ),
                            dailyWeather = nowWeatherMapper.transform(weatherResult.data.dailyData.predictions.first())
                                .mutateValues(
                                    hourlyNow?.temperature?.toCelsius(),
                                    hourlyNow?.thermalSensation?.toCelsius()
                                )
                        )
                    }
                    handleEvent(OnUploadLoadingState(false))
                }
            }
        }
    }
}


fun List<HourlyWeatherBo>.findClosestDate() = with(LocalDateTime.now(ZoneId.of("Europe/Madrid"))) {
    this@findClosestDate.minByOrNull {
        kotlin.math.abs(it.completeTime.minute - minute).toLong() +
                kotlin.math.abs(it.completeTime.hour - hour).toLong() * 60
    }
}


