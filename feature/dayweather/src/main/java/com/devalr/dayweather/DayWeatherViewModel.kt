package com.devalr.dayweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalr.dayweather.interactions.Event
import com.devalr.dayweather.interactions.Event.ChangeCity
import com.devalr.dayweather.interactions.Event.LoadScreen
import com.devalr.dayweather.interactions.Event.OnUploadErrorState
import com.devalr.dayweather.interactions.Event.OnUploadLoadingState
import com.devalr.dayweather.interactions.State
import com.devalr.dayweather.mergers.HourlyMerger
import com.devalr.domain.repositories.GeminiRepository
import com.devalr.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DayWeatherViewModel(
    private val geminiRepository: GeminiRepository,
    private val weatherRepository: WeatherRepository,
    private val hourlyMerger: HourlyMerger
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
            LoadScreen -> loadData ()

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
            val weatherData = weatherRepository.fetchHourlyWeather("13034")
            val weatherData2 = weatherRepository.fetchDailyWeather("13034")
            weatherData?.predictions?.let {
                _state.update { currentState ->
                    currentState.copy(weatherByHours = hourlyMerger.merge(it, 2, 24))
                }
            }
            handleEvent(OnUploadLoadingState(false))
        }
    }
}
