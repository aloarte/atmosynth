package com.devalr.cityselector.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.cityselector.R
import com.devalr.domain.model.CityBo
import com.devalr.framework.components.AtmosButton
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.theme.AtmosynthTheme


@Composable
fun SelectedCitiesScreen(
    selectedCities: List<CityBo>,
    activeCity: CityBo,
    onActivateCity: (CityBo) -> Unit,
    onNavigateSearchCity: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            item { City(city = activeCity, cityClicked = onActivateCity) }
            item { AtmosSeparator(size = 10.dp, type = SeparatorType.Vertical) }
            items(selectedCities) { city -> City(city = city, cityClicked = onActivateCity) }

        }
        AtmosButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(R.string.search_city),
            onClick = onNavigateSearchCity
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SelectedCitiesScreenPreview() {
    AtmosynthTheme {
        SelectedCitiesScreen(
            selectedCities = listOf(
                CityBo(
                    name = "Madrid", population = "10000", id = "id10000"
                ), CityBo(
                    name = "Ciudad Real", population = "20000", id = "id10020"
                )
            ),
            activeCity = CityBo(
                name = "Madrid", population = "10000", id = "id10000"
            ),
            onNavigateSearchCity = {},
            onActivateCity = {}
        )
    }

}