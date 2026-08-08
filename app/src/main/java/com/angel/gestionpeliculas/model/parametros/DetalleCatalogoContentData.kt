package com.angel.gestionpeliculas.model.parametros

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.angel.gestionpeliculas.model.CountryProviders
import com.angel.gestionpeliculas.model.ResenaPublicaResponse
import com.angel.gestionpeliculas.model.TMDBCollection
import com.angel.gestionpeliculas.model.TMDBCredits
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.model.TMDBSimilarResponse
import com.angel.gestionpeliculas.model.TMDBVideoResponse

data class DetalleCatalogoContentData(
    val detalleTMDB: TMDBMovie?,
    val watchProviders: CountryProviders?,
    val resenasPublicas: List<ResenaPublicaResponse>,
    val video: TMDBVideoResponse?,
    val credits: TMDBCredits?,
    val collection: TMDBCollection?,
    val similar: TMDBSimilarResponse?,
    val notaSlider: Float,
    val opinion: String?,
    val cargando: Boolean?,
    val onNotaChange: (Float) -> Unit,
    val onOpinionChange: (String) -> Unit,
    val modifier: Modifier,
    val onResenasPublicas: () -> Unit,
    val onBack: () -> Unit,
    val onGuardar: () -> Unit,
    val navController: NavController,
)
