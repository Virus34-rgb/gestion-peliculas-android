package com.angel.gestionpeliculas.model.parametros

import java.time.LocalDate

data class CrearResenaRequestData(
    val tmdbId: Long?,
    val nombre: String,
    val generos: List<String>,
    val fecha: LocalDate,
    val posterPath: String?,
    val nota: Double,
    val opinion: String?,
)
