package com.angel.gestion_peliculas.model

import java.time.LocalDate


data class Resena(
    val resenaId: Long?,
    val peliculaId: Long?,
    val tmdbId: Long?,
    val nombre: String,
    val generos: List<String>,
    val fecha: LocalDate,
    val posterPath: String?,
    val nota: Double,
    val opinion: String?,
    val fechaPublicacion: LocalDate?
)
