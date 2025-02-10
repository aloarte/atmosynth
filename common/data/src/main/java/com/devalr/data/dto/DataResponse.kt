package com.devalr.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse(
    @SerialName("descripcion")
    val requestDescription: String,
    @SerialName("estado")
    val requestState: Int,
    @SerialName("datos")
    val requestDataUrl: String,
    @SerialName("metadatos")
    val requestMetadataUrl: String
)
