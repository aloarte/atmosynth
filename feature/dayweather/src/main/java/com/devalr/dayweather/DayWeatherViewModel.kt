package com.devalr.dayweather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalr.domain.GeminiRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DayWeatherViewModel : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.onStart { generateDaySummary() }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    private val repository = GeminiRepositoryImpl()

    private fun generateDaySummary() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repository.generateDaySummary("Cold")

            _state.update { currentState ->
                currentState.copy(promptResult = result.joinToString())
            }
        }
    }

}