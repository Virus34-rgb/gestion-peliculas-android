package com.angel.gestionpeliculas.network

import com.angel.gestionpeliculas.model.TMDBCollection
import com.angel.gestionpeliculas.model.TMDBCredits
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.model.TMDBResponse
import com.angel.gestionpeliculas.model.TMDBSimilarResponse
import com.angel.gestionpeliculas.model.TMDBVideoResponse
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

    @GET("3/movie/{movieId}/videos")
    suspend fun getVideos(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
    ): Response<TMDBVideoResponse>

    @GET("3/movie/{movieId}/credits")
    suspend fun getCredits(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
    ): Response<TMDBCredits>

    @GET("3/movie/{movieId}/similar")
    suspend fun getSimilar(
        @Path("movieId") movieId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
    ): Response<TMDBSimilarResponse>

    @GET("3/collection/{collectionId}")
    suspend fun getCollection(
        @Path("collectionId") collectionId: Long,
        @Query("api_key") apiKey: String,
        @Query("language") language: String = "es-ES",
    ): Response<TMDBCollection>
}
