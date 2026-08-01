package com.angel.gestionpeliculas.network

import android.util.Log
import com.google.gson.GsonBuilder
import com.google.gson.JsonDeserializer
import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDate
import java.util.concurrent.TimeUnit
import kotlin.jvm.java

object RetrofitClient {
    private const val BASE_URL = "https://servidorapp-qkqh.onrender.com/"
    private val gson =
        GsonBuilder()
            .registerTypeAdapter(
                LocalDate::class.java,
                JsonDeserializer { json, _, _ -> LocalDate.parse(json.asString) },
            ).registerTypeAdapter(
                LocalDate::class.java,
                JsonSerializer<LocalDate> { src, _, _ -> JsonPrimitive(src.toString()) },
            ).create()
    private val logging =
        HttpLoggingInterceptor { mensaje ->
            Log.d("HTTP_BODY", mensaje)
        }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    private val client =
        OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest =
                    if (TokenManager.tokenTM != null) {
                        request
                            .newBuilder()
                            .header("Authorization", "Bearer ${TokenManager.tokenTM}")
                            .build()
                    } else {
                        request
                    }
                chain.proceed(newRequest)
            }.addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                if (response.code == 401 && request.url.encodedPath != "/auth/login") {
                    Log.d("HTTP", "Sesion expirada")
                    TokenManager.onSesionExpirada?.invoke()
                }
                response
            }
            // .addInterceptor(logging)
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()

    private val retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    val api = retrofit.create(PeliculaApi::class.java)
}
