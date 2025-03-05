package com.devalr.cityselector.interactions

import com.devalr.domain.model.CityBo

sealed class Event {
    data object LoadScreen : Event()
    data class OnUploadLoadingCitiesState(val isLoading: Boolean) : Event()
    data class OnActivateCity(val city: CityBo) : Event()
    data class OnSelectCity(val city: CityBo) : Event()
}