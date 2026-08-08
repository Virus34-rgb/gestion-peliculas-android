package com.angel.gestionpeliculas.repository

import com.angel.gestionpeliculas.BuildConfig
import com.angel.gestionpeliculas.model.CountryProviders
import com.angel.gestionpeliculas.model.TMDBCollection
import com.angel.gestionpeliculas.model.TMDBCredits
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.model.TMDBSimilarResponse
import com.angel.gestionpeliculas.model.TMDBVideoResponse
import com.angel.gestionpeliculas.network.TMDBClient

class TMDBRepository {
    suspend fun getWatchProviders(tmdbId: Long): CountryProviders? {
        val response = TMDBClient.api.getWatchProviders(tmdbId, BuildConfig.TMDB_API_KEY)
        return if (response.isSuccessful) {
            response.body()?.results?.get("ES")
        } else {
            null
        }
    }

    suspend fun searchTMDB(query: String): List<TMDBMovie> {
        val responseEs = TMDBClient.api.getFilmTMDB(query, BuildConfig.TMDB_API_KEY, "es-ES")
        val responseEn = TMDBClient.api.getFilmTMDB(query, BuildConfig.TMDB_API_KEY, "en-US")
        val resultEs =
            if (responseEs.isSuccessful) responseEs.body()?.results ?: emptyList() else emptyList()
        val resultEn =
            if (responseEn.isSuccessful) responseEn.body()?.results ?: emptyList() else emptyList()
        return (resultEs + resultEn).distinctBy { it.id }
    }

    suspend fun getMovieDetail(movieId: Long): TMDBMovie? {
        val response =
            TMDBClient.api.getMovieDetail(
                movieId,
                BuildConfig.TMDB_API_KEY,
            )
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getVideos(movieId: Long): TMDBVideoResponse? {
        val response = TMDBClient.api.getVideos(movieId, BuildConfig.TMDB_API_KEY)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getCredits(movieId: Long): TMDBCredits? {
        val response = TMDBClient.api.getCredits(movieId, BuildConfig.TMDB_API_KEY)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getSimilar(movieId: Long): TMDBSimilarResponse? {
        val response = TMDBClient.api.getSimilar(movieId, BuildConfig.TMDB_API_KEY)
        return if (response.isSuccessful) response.body() else null
    }

    suspend fun getCollection(collectionId: Long): TMDBCollection? {
        val response = TMDBClient.api.getCollection(collectionId, BuildConfig.TMDB_API_KEY)
        return if (response.isSuccessful) response.body() else null
    }
}
