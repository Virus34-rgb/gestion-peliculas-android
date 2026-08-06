package com.angel.gestionpeliculas.model.parametros

import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import com.angel.gestionpeliculas.model.TMDBMovie
import java.time.LocalDate

data class PeliculaFormContentData(
    val navController: NavController,
    val titulo: String,
    val nombre: String,
    val onNombreChange: (String) -> Unit,
    val peliculasTMDB: List<TMDBMovie>?,
    val onSeleccionarTMDB: (TMDBMovie) -> Unit,
    val generosSeleccionados: MutableList<String>,
    val generosDisponibles: List<String>,
    val fecha: LocalDate?,
    val onFechaSeleccionada: (LocalDate?) -> Unit,
    val notaSlider: Float,
    val onNotaChange: (Float) -> Unit,
    val opinion: String?,
    val onOpinionChange: (String?) -> Unit,
    val posterPath: String?,
    val onPosterChange: (String?) -> Unit,
    val tmdbId: Long?,
    val onTmdbIdChange: (Long?) -> Unit,
    val cargando: Boolean?,
    val intentoEnviar: Boolean,
    val onGuardar: () -> Unit,
    val padding: PaddingValues,
)
