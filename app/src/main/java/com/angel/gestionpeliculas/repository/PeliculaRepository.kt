package com.angel.gestionpeliculas.repository

import android.util.Log
import com.angel.gestionpeliculas.BuildConfig
import com.angel.gestionpeliculas.model.CountryProviders
import com.angel.gestionpeliculas.model.LoginData
import com.angel.gestionpeliculas.model.PeliculaResponse
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.model.ResenaPublicaResponse
import com.angel.gestionpeliculas.model.ResenaRequest
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.network.RetrofitClient
import com.angel.gestionpeliculas.network.TMDBClient

class PeliculaRepository {
    private val api = RetrofitClient.api

    suspend fun getAll(): List<Resena> {
        val response = api.getAll()
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getById(id: Long): Resena? {
        val response = api.getById(id)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun add(r: ResenaRequest): Boolean {
        val response = api.add(r)
        return response.isSuccessful
    }

    suspend fun delete(id: Long): Boolean {
        val response = api.delete(id)
        return response.isSuccessful
    }

    suspend fun update(
        id: Long,
        r: ResenaRequest,
    ): Boolean {
        val response = api.update(id, r)
        return response.isSuccessful
    }

    suspend fun getAllFiltered(
        filtro: String,
        ascendente: Boolean,
    ): List<Resena> {
        val response = api.getAllFiltered(filtro, ascendente)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun buscarPorTmdb(tmdbId: Long): PeliculaResponse? {
        val response = api.buscarPorTMDB(tmdbId)
        return if (response.isSuccessful) {
            response.body()
        } else {
            null
        }
    }

    suspend fun resenasDePelicula(peliculaId: Long): List<ResenaPublicaResponse> {
        val response = api.resenasDePelicula(peliculaId)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            emptyList()
        }
    }

    suspend fun getWatchProviders(tmdbId: Long): CountryProviders? {
        val response = TMDBClient.api.getWatchProviders(tmdbId, BuildConfig.TMDB_API_KEY)
        return if (response.isSuccessful) {
            response.body()?.results?.get("ES")
        } else {
            null
        }
    }

    suspend fun login(data: LoginData): Pair<Boolean, String?> {
        val response = api.login(data)
        return Pair(response.isSuccessful, response.body()?.token)
    }

    suspend fun register(data: LoginData): Boolean {
        val response = api.register(data)
        return response.isSuccessful
    }

    suspend fun searchTMDB(query: String): List<TMDBMovie> {
        val responseEs = TMDBClient.api.getFilmTMDB(query, BuildConfig.TMDB_API_KEY, "es-ES")
        val responseEn = TMDBClient.api.getFilmTMDB(query, BuildConfig.TMDB_API_KEY, "en-US")
        val resultEs = if (responseEs.isSuccessful) responseEs.body()?.results ?: emptyList() else emptyList()
        val resultEn = if (responseEn.isSuccessful) responseEn.body()?.results ?: emptyList() else emptyList()
        return (resultEs + resultEn).distinctBy { it.id }
    }

    suspend fun getMovieDetail(
        movieId: Long,
        language: String = "es-ES",
    ): TMDBMovie? {
        val response =
            TMDBClient.api.getMovieDetail(
                movieId,
                BuildConfig.TMDB_API_KEY,
                language,
            )

        Log.d("TMDB", "Código: ${response.code()}")
        Log.d("TMDB", "Mensaje: ${response.message()}")
        Log.d("TMDB", "Error: ${response.errorBody()?.string()}")

        return if (response.isSuccessful) response.body() else null
    }
}
