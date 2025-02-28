package com.devalr.cityselector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalr.cityselector.interactions.Event
import com.devalr.cityselector.interactions.Event.LoadScreen
import com.devalr.cityselector.interactions.Event.OnUploadLoadingCitiesState
import com.devalr.cityselector.interactions.State
import com.devalr.domain.repositories.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CityViewModel(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state
        .onStart {
            launchEvent(LoadScreen)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    fun launchEvent(event: Event) {
        when (event) {
            LoadScreen -> loadData()
            is OnUploadLoadingCitiesState -> updateLoadingState(event.isLoading)
        }
    }


    private fun updateLoadingState(loading: Boolean) {
        _state.update { currentState ->
            currentState.copy(loadingStates = currentState.loadingStates.copy(loadingCities = loading))
        }
    }


    private fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        launchEvent(OnUploadLoadingCitiesState(true))
        val weatherResult = cityRepository.fetchCities()
        Log.d("ALRALR", "$weatherResult")
    }
}