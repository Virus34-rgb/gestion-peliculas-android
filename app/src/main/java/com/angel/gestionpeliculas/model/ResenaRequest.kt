package com.angel.gestionpeliculas.model

import java.time.LocalDate

data class ResenaRequest(
    val tmdbId: Long?,
    val nombre: String,
    val generos: List<String>,
    val fecha: LocalDate,
    val posterPath: String?,
    val nota: Double,
    val opinion: String?,
)
