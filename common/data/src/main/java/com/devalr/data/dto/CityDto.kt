package com.devalr.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CityDto(
    @SerialName("latitud") val latitude: String,
    @SerialName("id_old") val oldId: String?=null,
    @SerialName("url") val url: String,
    @SerialName("latitud_dec") val latitudeDecimal: String,
    @SerialName("altitud") val altitude: String,
    @SerialName("capital") val capital: String,
    @SerialName("num_hab") val population: String,
    @SerialName("zona_comarcal") val regionalZone: String,
    @SerialName("destacada") val isFeatured: String,
    @SerialName("nombre") val name: String,
    @SerialName("longitud_dec") val longitudeDecimal: String,
    @SerialName("id") val id: String,
    @SerialName("longitud") val longitude: String
)