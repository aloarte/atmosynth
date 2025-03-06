package com.devalr.cityselector.compose

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.cityselector.R
import com.devalr.domain.model.CityBo
import com.devalr.framework.components.AtmosButton
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
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
            .background(MaterialTheme.colorScheme.onTertiary)
            .padding(25.dp)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
        ) {
            item {
                LabelIconText(
                    text = R.string.current_location_label,
                    icon = R.drawable.icon_home_location
                )
            }
            item { City(city = activeCity, cityClicked = onActivateCity) }
            item { AtmosSeparator(size = 30.dp, type = SeparatorType.Vertical) }
            item {
                LabelIconText(
                    text = R.string.saved_location_label,
                    icon = R.drawable.icon_saved_location
                )
            }
            items(selectedCities) { city ->
                City(city = city, cityClicked = onActivateCity)
                AtmosSeparator(type = SeparatorType.Vertical, size = 5.dp)
            }

        }
        AtmosButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = stringResource(R.string.search_city),
            onClick = onNavigateSearchCity
        )
    }
}

@Composable
private fun LabelIconText(@StringRes text: Int, @DrawableRes icon: Int) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.height(40.dp)) {
        Image(
            modifier = Modifier.size(18.dp),
            contentDescription = null,
            painter = painterResource(icon),
        )
        AtmosSeparator(size = 5.dp, type = SeparatorType.Horizontal)
        AtmosText(
            text = stringResource(text),
            type = TextType.LabelL
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