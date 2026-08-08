package com.angel.gestionpeliculas.model

data class TMDBMovie(
    val id: Long,
    val title: String,
    val releaseDate: String?,
    val genreIds: List<Long> = emptyList(),
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val voteAverage: Double,
    val voteCount: Int,
    val genres: List<TMDBGenre>? = null,
    val overview: String? = null,
    val runtime: Int? = null,
    val belongsToCollection: TMDBCollection? = null,
    val adult: Boolean,
    val originalLanguage: String,
    val originalTitle: String?,
    val status: String?,
)

data class TMDBGenre(
    val id: Long,
    val name: String,
)

data class TMDBVideo(
    val id: String,
    val key: String,
    val name: String,
    val site: String,
    val type: String,
    val official: Boolean,
    val publishedAt: String,
)

data class TMDBCollection(
    val id: Long,
    val name: String,
    val overview: String?,
    val posterPath: String?,
    val backdropPath: String?,
    val parts: List<TMDBMovie>,
)

data class TMDBActor(
    val id: Long,
    val name: String,
    val character: String,
    val profilePath: String?,
    val order: Int,
)

data class TMDBCrew(
    val id: Long,
    val name: String,
    val job: String,
    val department: String,
    val profilePath: String?,
)

data class TMDBCredits(
    val id: Long,
    val cast: List<TMDBActor>,
    val crew: List<TMDBCrew>,
)

data class TMDBSimilarResponse(
    val page: Int,
    val results: List<TMDBMovie>,
    val totalPages: Int,
    val totalResults: Int,
)
