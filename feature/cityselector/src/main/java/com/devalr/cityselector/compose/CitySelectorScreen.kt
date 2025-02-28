package com.devalr.cityselector.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.devalr.cityselector.CityViewModel
import com.devalr.framework.screens.LoadingScreen
import org.koin.compose.koinInject

@Composable
fun CitySelectorScreen(viewModel: CityViewModel = koinInject()) {
    val state by viewModel.state.collectAsState()
    Column(modifier = Modifier.background(MaterialTheme.colorScheme.onTertiary)) {
        if (state.loadingStates.loadingCities) {
            LoadingScreen()
        }
    }
}