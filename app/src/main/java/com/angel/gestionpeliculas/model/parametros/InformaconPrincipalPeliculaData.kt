package com.angel.gestionpeliculas.model.parametros

data class InformaconPrincipalPeliculaData(
    val titulo: String,
    val generos: List<String>,
    val valoracionTMDB: Double,
    val numVotosTMDB: Int,
    val valoracion: Double,
    val numResenas: Int,
    val fecha: String,
)
