package com.devalr.dayweather.model.enums

import androidx.annotation.DrawableRes
import com.devalr.dayweather.R

enum class WindDirectionText(val text: String, @DrawableRes val drawable: Int) {
    N("Norte", R.drawable.icon_south),
    NE("Noreste", R.drawable.icon_south_west),
    E("Este", R.drawable.icon_west),
    SE("Sureste", R.drawable.icon_north_west),
    S("Sur", R.drawable.icon_north),
    SW("Suroeste", R.drawable.icon_north_east),
    W("Oeste", R.drawable.icon_east),
    NW("Noroeste", R.drawable.icon_south_east),
    None("Ninguna", R.drawable.icon_no_direction_)
}
