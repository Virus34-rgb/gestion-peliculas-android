package com.angel.gestionpeliculas.model.parametros

import com.angel.gestionpeliculas.model.TMDBMovie
import java.time.LocalDate

data class SeleccionarPeliculaTMDBData(
    val pelicula: TMDBMovie,
    val onNombre: (String) -> Unit,
    val onFecha: (LocalDate?) -> Unit,
    val onPoster: (String?) -> Unit,
    val onGeneros: (List<String>) -> Unit,
    val onTmdbId: (Long?) -> Unit,
)
