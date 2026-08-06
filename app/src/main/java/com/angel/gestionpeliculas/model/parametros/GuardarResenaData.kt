package com.angel.gestionpeliculas.model.parametros

import com.angel.gestionpeliculas.viewmodel.PeliculaViewModel
import java.time.LocalDate

data class GuardarResenaData(
    val id: Long?,
    val viewModel: PeliculaViewModel,
    val onIntentoEnviar: (Boolean) -> Unit,
    val nombre: String,
    val generosSeleccionados: List<String>,
    val fecha: LocalDate?,
    val tmdbId: Long?,
    val posterPath: String?,
    val notaSlider: Float,
    val opinion: String?,
)
