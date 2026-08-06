package com.angel.gestionpeliculas.network

import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.model.TMDBResponse
import com.angel.gestionpeliculas.model.WatchProvidersResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TMDBApi {
    @GET("/3/search/movie")
    suspend fun getFilmTMDB(
        @Query("query") query: String,
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("sort_by") sortBy: String = "popularity.desc",
    ): Response<TMDBResponse>

    @GET("3/movie/{movieId}/watch/providers")
    suspend fun getWatchProviders(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String,
    ): Response<WatchProvidersResponse>

    @GET("3/movie/{movieId}")
    suspend fun getMovieDetail(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
    ): Response<TMDBMovie>
}
