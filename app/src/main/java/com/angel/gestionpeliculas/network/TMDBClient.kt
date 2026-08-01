package com.angel.gestionpeliculas.network

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.jvm.java

object TMDBClient {
    private const val BASE_URL = "https://api.themoviedb.org/"
    private val gson = GsonBuilder().create()

    private val retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    val api = retrofit.create(TMDBApi::class.java)
}
