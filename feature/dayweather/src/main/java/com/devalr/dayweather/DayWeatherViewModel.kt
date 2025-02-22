package com.devalr.dayweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalr.dayweather.extensions.toCelsius
import com.devalr.dayweather.interactions.Event
import com.devalr.dayweather.interactions.Event.*
import com.devalr.dayweather.interactions.State
import com.devalr.dayweather.mergers.HourlyMerger
import com.devalr.dayweather.model.PromptStateVo
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.dayweather.model.now.WindState
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.daily.DailyWeatherBo
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

class DayWeatherViewModel(
    private val geminiRepository: GeminiRepository,
    private val weatherUseCase: WeatherUseCase,
    private val hourlyMerger: HourlyMerger,
    private val nowWeatherMapper: Mapper<DailyWeatherBo, NowWeatherDataVo>
) : ViewModel() {

    private var isDataLoaded = false

    private lateinit var weatherPromptData: String

    private val _state = MutableStateFlow(State())
    val state = _state
        .onStart {
            if (!isDataLoaded) {
                isDataLoaded = true
                launchEvent(LoadScreen)
            }
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    fun launchEvent(event: Event) {
        when (event) {
            LoadScreen -> loadData()
            OnRetryDailySummaryPrompt -> launchAiQueryWeatherSummary(weatherPromptData)
            ChangeCity -> TODO()
            is OnUploadErrorState -> updateErrorState(event.error)
            is OnUploadLoadingState -> updateLoadingState(event.loading)
            is OnUploadHumidityDetailVisibility -> changeHumidityDetailVisibility(event.isVisible)
            is OnUploadWindDetailVisibility -> changeWindDetailVisibility(event.isVisible)
            is OnStartHumidityDetail -> launchAiQueryHumiditySummary(event.humidityData)
            is OnStartWindDetail -> launchAiQueryWindSummary(event.wind)
        }
    }

    private fun changeHumidityDetailVisibility(visible: Boolean) {
        _state.update { currentState ->
            currentState.copy(loadingStates = currentState.loadingStates.copy(displayHumidityDetail = visible))
        }
    }

    private fun changeWindDetailVisibility(visible: Boolean) {
        _state.update { currentState ->
            currentState.copy(loadingStates = currentState.loadingStates.copy(displayHumidityDetail = visible))
        }
    }

    private fun updateErrorState(error: Boolean) {
        _state.update { currentState ->
            currentState.copy(loadingStates = currentState.loadingStates.copy(errorReceived = error))
        }
    }

    private fun updateLoadingState(loading: Boolean) {
        _state.update { currentState ->
            currentState.copy(loadingStates = currentState.loadingStates.copy(loadingWeather = loading))
        }
    }


    private fun launchAiQueryWindSummary(wind: WindState?) = viewModelScope.launch(Dispatchers.IO) {
        launchEvent(OnUploadHumidityDetailVisibility(true))
        _state.update { currentState ->
            currentState.copy(promptWind = PromptStateVo(loadingAiPrompt = true))
        }
        val promptResult = geminiRepository.generateWindSummary(
            wind.toString()
        )
        _state.update { currentState ->
            currentState.copy(
                promptWind = PromptStateVo(
                    promptResult = promptResult,
                    loadingAiPrompt = false
                )
            )
        }
    }

    private fun launchAiQueryHumiditySummary(humidityData: WeatherMaxMin?) =
        viewModelScope.launch(Dispatchers.IO) {
            launchEvent(OnUploadHumidityDetailVisibility(true))
            _state.update { currentState ->
                currentState.copy(promptHumidity = PromptStateVo(loadingAiPrompt = true))
            }
            val promptResult = geminiRepository.generateHumiditySummary(
                humidityData.toString()
            )
            _state.update { currentState ->
                currentState.copy(
                    promptHumidity = PromptStateVo(
                        promptResult = promptResult,
                        loadingAiPrompt = false
                    )
                )
            }
        }

    private fun launchAiQueryWeatherSummary(promptData: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { currentState ->
                currentState.copy(promptSummary = PromptStateVo(loadingAiPrompt = true))
            }
            val promptResult = geminiRepository.generateDaySummary(promptData)
            _state.update { currentState ->
                currentState.copy(
                    promptSummary = PromptStateVo(
                        promptResult = promptResult,
                        loadingAiPrompt = false
                    )
                )
            }
        }

    private fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        launchEvent(OnUploadLoadingState(true))

        when (val weatherResult =
            weatherUseCase.execute(viewModelScope, WeatherUseCase.Params("13034"))) {
            is Result.Error -> {
                launchEvent(OnUploadLoadingState(false))
            }

            is Result.Success -> {
                weatherPromptData = weatherResult.data.dailyData.predictions.first().toString()
                    .processForAI()
                launchAiQueryWeatherSummary(weatherPromptData)
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
                launchEvent(OnUploadLoadingState(false))
            }
        }
    }
}



