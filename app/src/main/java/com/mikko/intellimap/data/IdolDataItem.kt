package com.mikko.intellimap.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdolDataItem(
    @SerialName("description")
    val description: String,
    @SerialName("name")
    val name: String,
    @SerialName("slug")
    val slug: String
)