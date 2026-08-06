package com.angel.gestionpeliculas.model.parametros

import androidx.compose.ui.Modifier
import com.angel.gestionpeliculas.model.CountryProviders
import com.angel.gestionpeliculas.model.ResenaPublicaResponse
import com.angel.gestionpeliculas.model.TMDBMovie

data class DetalleCatalogoContentData(
    val detalleTMDB: TMDBMovie?,
    val watchProviders: CountryProviders?,
    val resenasPublicas: List<ResenaPublicaResponse>,
    val notaSlider: Float,
    val opinion: String?,
    val cargando: Boolean?,
    val onNotaChange: (Float) -> Unit,
    val onOpinionChange: (String) -> Unit,
    val modifier: Modifier,
    val onResenasPublicas: () -> Unit,
    val onBack: () -> Unit,
    val onGuardar: () -> Unit,
)
