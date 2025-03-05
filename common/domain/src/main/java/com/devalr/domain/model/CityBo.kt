package com.devalr.domain.model

data class CityBo(
    val id: String,
    val population: String,
    val name: String,
    val geographicData: GeographicDataBo? = null,
    val selected: Boolean = false,
    val active: Boolean = false
)