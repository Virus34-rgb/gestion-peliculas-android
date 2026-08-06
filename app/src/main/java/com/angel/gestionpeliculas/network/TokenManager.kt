package com.angel.gestionpeliculas.network

import android.content.Context
import android.util.Base64
import android.util.Log
import com.angel.gestionpeliculas.datastore.TokenDataStore
import org.json.JSONObject

object TokenManager {
    var tokenTM: String? = null
    var onSesionExpirada: (() -> Unit)? = null

    suspend fun login(
        context: Context,
        token: String?,
    ) {
        if (token == null) return
        tokenTM = token
        TokenDataStore.saveToken(context, token)
    }

    suspend fun loadFromDataStore(context: Context) {
        tokenTM = TokenDataStore.readToken(context)
    }

    suspend fun logout(context: Context) {
        tokenTM = null
        TokenDataStore.delToken(context)
    }

    fun haySesionValida(): Boolean {
        val token = tokenTM ?: return false
        return !isTokenExpirado(token)
    }

    @Suppress("TooGenericExceptionCaught")
    private fun isTokenExpirado(token: String): Boolean =
        try {
            val valor = 1000
            val payload = token.split(".")[1]
            val decoded = Base64.decode(payload, Base64.URL_SAFE or Base64.NO_WRAP)
            val json = JSONObject(String(decoded))
            val exp = json.getLong("exp")
            System.currentTimeMillis() >= exp * valor
        } catch (e: Exception) {
            Log.e("TokenManager", e.toString(), e)
            true
        }

    @Suppress("TooGenericExceptionCaught")
    fun getUsername(): String? {
        val token = tokenTM ?: return null
        return try {
            val payload = token.split(".")[1]
            val decoded = Base64.decode(payload, Base64.URL_SAFE or Base64.NO_WRAP)
            val json = JSONObject(String(decoded))
            json.getString("sub")
        } catch (e: Exception) {
            Log.e("PeliculaViewModel", e.toString(), e)
            null
        }
    }
}
