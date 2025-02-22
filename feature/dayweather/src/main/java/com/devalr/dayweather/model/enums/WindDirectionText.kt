package com.devalr.dayweather.model.enums

import androidx.annotation.DrawableRes
import com.devalr.dayweather.R

enum class WindDirectionText(val text: String, @DrawableRes val drawable: Int) {
    N("Norte", R.drawable.icon_wind_north),
    NE("Noreste", R.drawable.icon_wind_north_east),
    E("Este", R.drawable.icon_wind_east),
    SE("Sureste", R.drawable.icon_wind_south_east),
    S("Sur", R.drawable.icon_wind_south),
    SW("Suroeste", R.drawable.icon_wind_south_west),
    W("Oeste", R.drawable.icon_wind_west),
    NW("Noroeste", R.drawable.icon_wind_north_west),
    None("Ninguna", -1)
}
