package com.devalr.dayweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalr.dayweather.interactions.Event
import com.devalr.dayweather.interactions.Event.ChangeCity
import com.devalr.dayweather.interactions.Event.LoadScreen
import com.devalr.dayweather.interactions.State
import com.devalr.dayweather.mergers.HourlyMerger
import com.devalr.domain.repositories.GeminiRepository
import com.devalr.domain.repositories.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DayWeatherViewModel(
    private val geminiRepository: GeminiRepository,
    private val weatherRepository: WeatherRepository,
    private val hourlyMerger: HourlyMerger
) : ViewModel() {
    private val _state = MutableStateFlow(State())
    val state =
        _state
            .onStart { handleEvent(LoadScreen) }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    fun handleEvent(event: Event) {
        when (event) {
            ChangeCity -> TODO()
            LoadScreen -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch(Dispatchers.IO) {
            val weatherData = weatherRepository.fetchDailyWeather("13034")
            weatherData?.predictions?.let {
                hourlyMerger.merge(it)
            }

            /*_state.update { currentState ->
                currentState.copy(promptResult = resultW.joinToString())
            }*/
        }
    }
}
