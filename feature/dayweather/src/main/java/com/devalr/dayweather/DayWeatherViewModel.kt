package com.devalr.dayweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalr.dayweather.extensions.toCelsius
import com.devalr.dayweather.interactions.Event
import com.devalr.dayweather.interactions.Event.ChangeCity
import com.devalr.dayweather.interactions.Event.LoadScreen
import com.devalr.dayweather.interactions.Event.OnStartDailySummaryDetail
import com.devalr.dayweather.interactions.Event.OnStartHourlySummaryDetail
import com.devalr.dayweather.interactions.Event.OnStartHumidityDetail
import com.devalr.dayweather.interactions.Event.OnStartPrecipitationsDetail
import com.devalr.dayweather.interactions.Event.OnStartUvDetail
import com.devalr.dayweather.interactions.Event.OnStartWindDetail
import com.devalr.dayweather.interactions.Event.OnUploadErrorState
import com.devalr.dayweather.interactions.Event.OnUploadHourlyDetailVisibility
import com.devalr.dayweather.interactions.Event.OnUploadHumidityDetailVisibility
import com.devalr.dayweather.interactions.Event.OnUploadLoadingState
import com.devalr.dayweather.interactions.Event.OnUploadPrecipitationsDetailVisibility
import com.devalr.dayweather.interactions.Event.OnUploadUvDetailVisibility
import com.devalr.dayweather.interactions.Event.OnUploadWeatherDetailVisibility
import com.devalr.dayweather.interactions.Event.OnUploadWindDetailVisibility
import com.devalr.dayweather.interactions.State
import com.devalr.dayweather.mergers.HourlyMerger
import com.devalr.dayweather.model.PromptStateVo
import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.hourly.HourlyWeatherVo
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.dayweather.model.now.WindState
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.FetchedWeatherData
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
    private lateinit var todayWeather: DailyWeatherBo

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
            ChangeCity -> TODO()
            is OnUploadErrorState -> updateErrorState(event.error)
            is OnUploadLoadingState -> updateLoadingState(event.loading)

            is OnUploadWeatherDetailVisibility -> changeWeatherDetailVisibility(event.isVisible)
            is OnUploadHourlyDetailVisibility -> changeHourlyDetailVisibility(event.isVisible)
            is OnUploadPrecipitationsDetailVisibility -> changePrecipitationsDetailVisibility(event.isVisible)
            is OnUploadHumidityDetailVisibility -> changeHumidityDetailVisibility(event.isVisible)
            is OnUploadWindDetailVisibility -> changeWindDetailVisibility(event.isVisible)
            is OnUploadUvDetailVisibility -> changeUvDetailVisibility(event.isVisible)

            OnStartDailySummaryDetail -> launchAiQueryWeatherSummary()
            is OnStartHourlySummaryDetail -> launchAiQueryHourlySummary(event.hourlyTime)
            is OnStartPrecipitationsDetail -> launchAiQueryPrecipitationsSummary(event.hourlyTime)
            is OnStartHumidityDetail -> launchAiQueryHumiditySummary(
                event.humidityData,
                event.temperatureData
            )

            is OnStartWindDetail -> launchAiQueryWindSummary(event.wind)
            is OnStartUvDetail -> launchAiQueryUvSummary(event.uv)
        }
    }


    private fun changeWeatherDetailVisibility(visible: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                loadingStates = currentState.loadingStates.copy(displayWeatherSummaryDetail = visible)
            )
        }
    }

    private fun changeHourlyDetailVisibility(visible: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                loadingStates = currentState.loadingStates.copy(displayHourlySummaryDetail = visible)
            )
        }
    }

    private fun changePrecipitationsDetailVisibility(visible: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                loadingStates = currentState.loadingStates.copy(displayPrecipitationsDetail = visible)
            )
        }
    }

    private fun changeHumidityDetailVisibility(visible: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                loadingStates = currentState.loadingStates.copy(displayHumidityDetail = visible)
            )
        }
    }

    private fun changeWindDetailVisibility(visible: Boolean) {
        _state.update { currentState ->
            currentState.copy(loadingStates = currentState.loadingStates.copy(displayWindDetail = visible))
        }
    }

    private fun changeUvDetailVisibility(visible: Boolean) {
        _state.update { currentState ->
            currentState.copy(loadingStates = currentState.loadingStates.copy(displayUvDetail = visible))
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

    private fun launchAiQueryWeatherSummary() =
        viewModelScope.launch(Dispatchers.IO) {
            launchEvent(OnUploadWeatherDetailVisibility(true))
            _state.update { currentState ->
                currentState.copy(
                    promptResults = currentState.promptResults.copy(
                        promptWeatherSummary = PromptStateVo(loadingAiPrompt = true)
                    )
                )
            }

            val promptResult = geminiRepository.generateDaySummary(weatherPromptData)

            _state.update { currentState ->
                currentState.copy(
                    promptResults = currentState.promptResults.copy(
                        promptWeatherSummary = PromptStateVo(
                            promptResult = promptResult,
                            loadingAiPrompt = false
                        )
                    )
                )
            }
        }

    private fun launchAiQueryHourlySummary(hourlyEvents: List<HourlyDataVo>) =
        viewModelScope.launch(Dispatchers.IO) {
            launchEvent(OnUploadHourlyDetailVisibility(true))
            _state.update { currentState ->
                currentState.copy(
                    promptResults = currentState.promptResults.copy(
                        promptHourlySummary = PromptStateVo(loadingAiPrompt = true)
                    )
                )
            }

            val promptResult = geminiRepository.generateHourlySummary(
                dataForPrompt = hourlyEvents.getCompletePromptForPrecipitationByHours()
            )

            _state.update { currentState ->
                currentState.copy(
                    promptResults = currentState.promptResults.copy(
                        promptHourlySummary = PromptStateVo(
                            promptResult = promptResult,
                            loadingAiPrompt = false
                        )
                    )
                )
            }
        }


    private fun launchAiQueryWindSummary(wind: WindState?) = viewModelScope.launch(Dispatchers.IO) {
        launchEvent(OnUploadWindDetailVisibility(true))
        _state.update { currentState ->
            currentState.copy(
                promptResults = currentState.promptResults.copy(
                    promptWind = PromptStateVo(
                        loadingAiPrompt = true
                    )
                )
            )
        }
        val promptResult = geminiRepository.generateWindSummary(
            windData = wind.toString()
        )
        _state.update { currentState ->
            currentState.copy(
                promptResults = currentState.promptResults.copy(
                    promptWind = PromptStateVo(
                        promptResult = promptResult,
                        loadingAiPrompt = false
                    )
                )
            )

        }
    }

    private fun launchAiQueryPrecipitationsSummary(hourlyEvents: List<HourlyWeatherVo>) =
        viewModelScope.launch(Dispatchers.IO) {
            launchEvent(OnUploadPrecipitationsDetailVisibility(true))
            _state.update { currentState ->
                currentState.copy(
                    promptResults = currentState.promptResults.copy(
                        promptPrecipitations = PromptStateVo(loadingAiPrompt = true)
                    )
                )
            }
            val promptResult = geminiRepository.generatePrecipitationsSummary(
                hourlyPrecipitations = hourlyEvents.getPromptForPrecipitationByHours(),
                todayPrecipitations = todayWeather.getPromptForTodayPrecipitations()
            )
            _state.update { currentState ->
                currentState.copy(
                    promptResults = currentState.promptResults.copy(
                        promptPrecipitations = PromptStateVo(
                            promptResult = promptResult,
                            loadingAiPrompt = false
                        )
                    )
                )
            }
        }

    private fun launchAiQueryHumiditySummary(
        humidityData: WeatherMaxMin?,
        temperatureData: WeatherMaxMin?
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            launchEvent(OnUploadHumidityDetailVisibility(true))


            _state.update { currentState ->
                currentState.copy(
                    promptResults = currentState.promptResults.copy(
                        promptHumidity = PromptStateVo(loadingAiPrompt = true)
                    )
                )
            }
            val promptResult = geminiRepository.generateHumiditySummary(
                humidityData = humidityData.toString(),
                temperatureData = temperatureData.toString()
            )
            _state.update { currentState ->
                currentState.copy(
                    promptResults = currentState.promptResults.copy(
                        promptHumidity = PromptStateVo(
                            promptResult = promptResult,
                            loadingAiPrompt = false
                        )
                    )
                )
            }
        }

    private fun launchAiQueryUvSummary(uv: String) = viewModelScope.launch(Dispatchers.IO) {
        launchEvent(OnUploadUvDetailVisibility(true))
        _state.update { currentState ->
            currentState.copy(
                promptResults = currentState.promptResults.copy(
                    promptUv = PromptStateVo(loadingAiPrompt = true)
                )
            )
        }
        val promptResult = geminiRepository.generateUvSummary(uv = uv)
        _state.update { currentState ->
            currentState.copy(
                promptResults = currentState.promptResults.copy(
                    promptUv = PromptStateVo(
                        promptResult = promptResult,
                        loadingAiPrompt = false
                    )
                )
            )
        }
    }

    private fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        launchEvent(OnUploadLoadingState(true))
        when (val weatherResult = weatherUseCase.execute(
            scope = viewModelScope,
            params = WeatherUseCase.Params("13034")
        )) {
            is Result.Error -> {
                launchEvent(OnUploadLoadingState(false))
            }

            is Result.Success -> {
                processWeatherData(weatherResult)
            }
        }
    }

    private fun processWeatherData(weatherResult: Result.Success<FetchedWeatherData>) {
        weatherPromptData = weatherResult.data.dailyData.getPromptForNowWeatherData()
        //launchAiQueryWeatherSummary(weatherPromptData)
        val hourlyNow = weatherResult.data.hourlyData.predictions.first()
            .hourlyData.findClosestDate()
        val weatherByHoursInRange = hourlyMerger.merge(
            weatherResult.data.hourlyData.predictions,
            2,
            24
        )
        todayWeather = weatherResult.data.dailyData.predictions.first()
        _state.update { currentState ->
            currentState.copy(
                weatherByHours = weatherByHoursInRange,
                dailyWeather = nowWeatherMapper.transform(todayWeather)
                    .mutateValues(
                        hourlyNow?.temperature?.toCelsius(),
                        hourlyNow?.thermalSensation?.toCelsius()
                    )
            )
        }
        launchEvent(OnUploadLoadingState(false))
    }
}

