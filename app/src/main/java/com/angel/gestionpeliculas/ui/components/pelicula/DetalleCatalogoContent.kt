package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.angel.gestionpeliculas.model.parametros.DetalleCatalogoContentData
import com.angel.gestionpeliculas.model.parametros.FormularioResenaRapidaData
import com.angel.gestionpeliculas.model.parametros.InformaconPrincipalPeliculaData

@Composable
fun DetalleCatalogoContent(param: DetalleCatalogoContentData) {
    var total: Double = 0.0
    for (resena in param.resenasPublicas) {
        total += resena.nota
    }
    val valoracion =
        if (param.resenasPublicas.isNotEmpty()) {
            param.resenasPublicas.map { it.nota }.average()
        } else {
            0.0
        }
    LazyColumn(modifier = param.modifier) {
        item {
            HeroPelicula(
                param.detalleTMDB!!.posterPath,
                param.onBack,
                param.detalleTMDB.title,
            )
            InformacionPrincipalPelicula(
                InformaconPrincipalPeliculaData(
                    param.detalleTMDB.title,
                    param.detalleTMDB.genres?.map { it.name } ?: emptyList(),
                    param.detalleTMDB.voteAverage,
                    param.detalleTMDB.voteCount,
                    valoracion,
                    param.resenasPublicas.size,
                    param.detalleTMDB.releaseDate,
                ),
            )
            TituloTexto("SINOPSIS", param.detalleTMDB.overview)
            DistribuidoresPelicula(param.watchProviders)
            ResenasPelicula(param.resenasPublicas, param.onResenasPublicas)
            FormularioResenaRapida(
                FormularioResenaRapidaData(
                    param.notaSlider,
                    onNotaChange = param.onNotaChange,
                    param.opinion,
                    onOpinionChange = param.onOpinionChange,
                    param.cargando != true,
                    { param.onGuardar() },
                ),
            )
        }
    }
}
