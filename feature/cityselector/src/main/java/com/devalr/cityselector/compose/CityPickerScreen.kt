package com.devalr.cityselector.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.cityselector.R
import com.devalr.domain.model.CityBo
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType

@Composable
fun CityPickerScreen(cities: List<CityBo>, onCitySelected: (CityBo) -> Unit) {
    var text by remember { mutableStateOf("") }
    var filteredCities by remember { mutableStateOf(cities) }
    LaunchedEffect(text) {
        filteredCities = cities.takeIf { text.isNotBlank() }
            ?.filter {
                it.name.contains(
                    text,
                    ignoreCase = true
                ) && it.selected.not() && it.active.not()
            } ?: emptyList()
    }
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.onTertiary)
            .padding(25.dp)

    ) {
        OutlinedTextField(
            value = text,
            leadingIcon = {
                Image(
                    painter = painterResource(id = R.drawable.icon_add_location),
                    contentDescription = null
                )
            },
            onValueChange = { text = it },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.onTertiary,
                unfocusedContainerColor = MaterialTheme.colorScheme.onTertiary,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
                unfocusedTextColor = MaterialTheme.colorScheme.onBackground
            ),
            label = {
                AtmosText(
                    text = stringResource(R.string.pick_city_title),
                    type = TextType.LabelM
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                }
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(filteredCities) { city ->
                City(city, onCitySelected)
                AtmosSeparator(type = SeparatorType.Vertical, size = 5.dp)
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun CitySelectorPreview() {
    CityPickerScreen(
        cities = listOf(
            CityBo(
                name = "Madrid",
                population = "10000",
                id = "id10000"
            ),
            CityBo(
                name = "Ciudad Real",
                population = "20000",
                id = "id10020"
            )
        ),
        onCitySelected = {}
    )
}