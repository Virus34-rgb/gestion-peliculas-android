package com.angel.gestionpeliculas.model

import java.time.LocalDate

data class PeliculaResponse(
    val peliculaId: Long,
    val tmdbId: Long?,
    val nombre: String,
    val generos: List<String>,
    val fecha: LocalDate,
    val posterPath: String?,
)
