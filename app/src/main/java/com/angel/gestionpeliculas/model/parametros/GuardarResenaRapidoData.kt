package com.angel.gestionpeliculas.model.parametros

import com.angel.gestionpeliculas.model.ResenaPublicaResponse
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.viewmodel.BuscarViewModel

data class GuardarResenaRapidoData(
    val buscarViewModel: BuscarViewModel,
    val miResenaExistente: ResenaPublicaResponse?,
    val tmdbId: Long,
    val detalleTMDB: TMDBMovie,
    val nota: Float,
    val opinion: String?,
)
