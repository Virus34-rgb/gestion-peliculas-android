package com.angel.gestionpeliculas.model

data class TMDBMovie(
    val id: Long,
    val title: String,
    val release_date: String,
    val genre_ids: List<Long> = emptyList(),
    val poster_path: String? = null,
    val vote_average: Double,
    val vote_count: Int,
    val genres: List<TMDBGenre>? = null,
    val overview: String? = null,
)

data class TMDBGenre(
    val id: Long,
    val name: String,
)
