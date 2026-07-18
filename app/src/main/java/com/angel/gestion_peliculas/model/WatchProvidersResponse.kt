package com.angel.gestion_peliculas.model

import com.google.gson.annotations.SerializedName

data class WatchProvidersResponse(
    val id: Long,
    val results: Map<String, CountryProviders>
)

data class CountryProviders(
    val link: String? = null,
    val flatrate: List<Provider>? = null,
    val rent: List<Provider>? = null,
    val buy: List<Provider>? = null
)

data class Provider(
    @SerializedName("provider_name") val providerName: String,
    @SerializedName("logo_path") val logoPath: String
)
