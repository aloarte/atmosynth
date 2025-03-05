package com.devalr.cityselector

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devalr.cityselector.interactions.Event
import com.devalr.cityselector.interactions.Event.LoadScreen
import com.devalr.cityselector.interactions.Event.OnActivateCity
import com.devalr.cityselector.interactions.Event.OnSelectCity
import com.devalr.cityselector.interactions.Event.OnUploadLoadingCitiesState
import com.devalr.cityselector.interactions.State
import com.devalr.domain.model.CityBo
import com.devalr.domain.repositories.CityRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CityViewModel(private val cityRepository: CityRepository) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state
        .onStart {
            Log.d("ALRALR","launchEvent")
            launchEvent(LoadScreen)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), State())

    fun launchEvent(event: Event) {
        when (event) {
            LoadScreen -> loadData()
            is OnUploadLoadingCitiesState -> updateLoadingState(event.isLoading)
            is OnSelectCity -> selectCity(event.city)
            is OnActivateCity -> activateCity(event.city)
        }
    }

    private fun updateLoadingState(loading: Boolean) {
        _state.update { currentState ->
            currentState.copy(
                loadingStates = currentState.loadingStates.copy(
                    loadingCities = loading
                )
            )
        }
    }

    private fun loadData() = viewModelScope.launch(Dispatchers.IO) {
        launchEvent(OnUploadLoadingCitiesState(true))
        Log.d("ALRALR","TRUE")
        val cities = cityRepository.fetchCities().also{
            Log.d("ALRALR","Cities: ${it.size}")
        }
        _state.update { currentState ->
            currentState.copy(
                loadingStates = currentState.loadingStates.copy(
                    loadingCities = false
                ),
                cities = cities
            )
        }
        Log.d("ALRALR","FALSE")

    }

    private fun selectCity(it: CityBo) = viewModelScope.launch(Dispatchers.IO) {
        cityRepository.selectCity(it)
        loadData()
    }

    private fun activateCity(it: CityBo) = viewModelScope.launch(Dispatchers.IO) {
        cityRepository.selectCity(it)
        cityRepository.activateCity(it)
        loadData()
    }
}