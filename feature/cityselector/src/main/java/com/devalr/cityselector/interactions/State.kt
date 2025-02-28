package com.devalr.cityselector.interactions

import com.devalr.domain.model.CityBo


data class State(
    val loadingStates: BooleanLoadingStates = BooleanLoadingStates(),
    val cities : List<CityBo> = emptyList()
)
