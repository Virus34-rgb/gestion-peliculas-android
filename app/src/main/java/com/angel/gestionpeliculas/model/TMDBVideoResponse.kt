package com.angel.gestionpeliculas.model

data class TMDBVideoResponse(
    val id: Long,
    val results: List<TMDBVideo>,
)

fun getTrailer(videos: TMDBVideoResponse?): TMDBVideo? {
    videos?.let { videos ->
        val trailer =
            videos.results.firstOrNull {
                it.site == "YouTube" &&
                    it.type == "Trailer" &&
                    it.official
            }
                ?: videos.results.firstOrNull {
                    it.site == "YouTube" &&
                        it.type == "Trailer"
                }
                ?: videos.results.firstOrNull {
                    it.site == "YouTube" &&
                        it.type == "Teaser"
                }
                ?: videos.results.firstOrNull {
                    it.site == "YouTube"
                }
        return trailer
    }
    return null
}
