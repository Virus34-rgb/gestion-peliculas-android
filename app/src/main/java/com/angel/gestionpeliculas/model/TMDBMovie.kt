package com.angel.gestionpeliculas.model

data class TMDBMovie(
    val id: Long,
    val title: String,
    val releaseDate: String,
    val genreIds: List<Long> = emptyList(),
    val posterPath: String? = null,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<TMDBGenre>? = null,
    val overview: String? = null,
)

data class TMDBGenre(
    val id: Long,
    val name: String,
)
