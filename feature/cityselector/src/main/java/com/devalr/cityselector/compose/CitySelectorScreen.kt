package com.devalr.cityselector.compose

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devalr.cityselector.CityViewModel
import com.devalr.cityselector.NavScreen
import com.devalr.cityselector.interactions.Event
import com.devalr.framework.screens.LoadingScreen
import org.koin.compose.koinInject

@Composable
fun CitySelectorScreen(viewModel: CityViewModel = koinInject(), citySelected:()->Unit) {
    val state by viewModel.state.collectAsState()
    Log.d("ALRALR","inside CSS")

    if (state.loadingStates.loadingCities) {
        Log.d("ALRALR","inside CSS 1")

        LoadingScreen()
    } else {
        Log.d("ALRALR","inside CSS 2")

        val selectedCities = state.cities.filter { it.selected && !it.active }

        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = if (selectedCities.isNotEmpty()) {
                NavScreen.SelectedCities.route
            } else {
                NavScreen.CitySearch.route
            }
        ) {
            composable(NavScreen.SelectedCities.route) {
                val activeCity = state.cities.firstOrNull { it.active } ?: throw Exception("Empty number of selected cities")
                SelectedCitiesScreen(
                    selectedCities = selectedCities,
                    activeCity = activeCity,
                    onActivateCity = { viewModel.launchEvent(Event.OnActivateCity(it)) },
                    onNavigateSearchCity = { navController.navigate(NavScreen.CitySearch.route) }
                )
            }
            composable(NavScreen.CitySearch.route) {
                CityPickerScreen(
                    cities = state.cities,
                    onCitySelected = {
                        viewModel.launchEvent(Event.OnActivateCity(it))
                        citySelected()
                    }
                )
            }
        }
    }
}