package com.devalr.cityselector

sealed class NavScreen(val route: String) {
    data object CitySearch : NavScreen("CitySearch")
    data object SelectedCities : NavScreen("SelectedCities")
}