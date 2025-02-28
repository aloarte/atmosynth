package com.devalr.cityselector.interactions

sealed class Event {
    data object LoadScreen : Event()
    data class OnUploadLoadingCitiesState(val isLoading: Boolean) : Event()
}