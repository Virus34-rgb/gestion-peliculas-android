package com.angel.gestionpeliculas.model.parametros

import com.angel.gestionpeliculas.model.TMDBMovie

data class BuscadorTMDBData(
    val nombre: String,
    val onNombreChange: (String) -> Unit,
    val resultados: List<TMDBMovie>?,
    val onPeliculaSeleccionada: (TMDBMovie) -> Unit,
    val enabled: Boolean,
    val isError: Boolean = false,
    val mensajeError: String? = null,
)
