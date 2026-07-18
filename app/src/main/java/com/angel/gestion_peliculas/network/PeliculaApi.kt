package com.angel.gestion_peliculas.network

import com.angel.gestion_peliculas.model.AuthResponse
import com.angel.gestion_peliculas.model.LoginData
import com.angel.gestion_peliculas.model.PeliculaResponse
import com.angel.gestion_peliculas.model.Resena
import com.angel.gestion_peliculas.model.ResenaPublicaResponse
import com.angel.gestion_peliculas.model.ResenaRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface PeliculaApi {
    @GET("/resenas")
    suspend fun getAll(): Response<List<Resena>>
    @GET("/resenas/{id}")
    suspend fun getById(@Path("id") id : Long): Response<Resena>
    @POST("/resenas")
    suspend fun add(@Body request : ResenaRequest) : Response<Unit>
    @DELETE("/resenas/{id}")
    suspend fun delete(@Path("id") id : Long) : Response<Unit>
    @PUT("/resenas/{id}")
    suspend fun update(@Path("id") id : Long,@Body request : ResenaRequest) :
            Response<Unit>
    @GET("/resenas/filtered")
    suspend fun getAllFiltered(
        @Query("filtro") filtro: String,
        @Query("ascendente") ascendente: Boolean
    ): Response<List<Resena>>
    @POST("/auth/login")
    suspend fun login(@Body data : LoginData) : Response<AuthResponse>
    @POST ("/auth/register")
    suspend fun register(@Body data : LoginData) : Response<Unit>
    @GET("/peliculas/tmdb/{tmdbId}")
    suspend fun buscarPorTMDB(@Path( "tmdbId") tmdbId : Long) : Response<PeliculaResponse>
    @GET("/resenas/pelicula/{peliculaId}")
    suspend fun resenasDePelicula(@Path("peliculaId") peliculaId : Long) : Response<List<ResenaPublicaResponse>>
}
