package com.angel.gestion_peliculas.datastore

import android.content.Context
import androidx.datastore.preferences.core.Preferences.Key
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore by preferencesDataStore(name = "session_prefs")
object TokenDataStore {
    val lugar : Key<String> =  stringPreferencesKey("jwt_token")


    suspend fun saveToken(context: Context, token: String) {
        context.dataStore.edit { prefs ->
            prefs[lugar] = token!!
        }
    }

    suspend fun readToken (context: Context) : String? {
        val prefs = context.dataStore.data.first()
        return prefs[lugar]
    }

    suspend fun delToken (context: Context){
        context.dataStore.edit { prefs ->
            prefs.remove(lugar)
        }
    }
}