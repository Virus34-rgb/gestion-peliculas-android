package com.angel.gestion_peliculas.model

import java.time.LocalDate

data class Pelicula(
    val id: Long?,
    val nombre: String,
    val nota: Double,
    val genero: List<String>,
    val fecha: LocalDate?,
    val posterPath: String?
)
