package com.angel.gestionpeliculas.repository

import com.angel.gestionpeliculas.model.LoginData
import com.angel.gestionpeliculas.model.PeliculaResponse
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.model.ResenaPublicaResponse
import com.angel.gestionpeliculas.model.ResenaRequest
import com.angel.gestionpeliculas.network.RetrofitClient

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

    suspend fun login(data: LoginData): Pair<Boolean, String?> {
        val response = api.login(data)
        return Pair(response.isSuccessful, response.body()?.token)
    }

    suspend fun register(data: LoginData): Boolean {
        val response = api.register(data)
        return response.isSuccessful
    }
}
