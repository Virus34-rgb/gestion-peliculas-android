package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angel.gestionpeliculas.model.CountryProviders
import com.angel.gestionpeliculas.model.ResenaPublicaResponse
import com.angel.gestionpeliculas.model.TMDBMovie

@Composable
fun DetalleCatalogoContent(
    detalleTMDB: TMDBMovie?,
    watchProviders: CountryProviders?,
    resenasPublicas: List<ResenaPublicaResponse>,
    notaSlider: Float,
    opinion: String?,
    cargando: Boolean?,
    onNotaChange: (Float) -> Unit,
    onOpinionChange: (String) -> Unit,
    modifier: Modifier,
    onResenasPublicas: () -> Unit,
    onBack: () -> Unit,
    onGuardar: () -> Unit,
) {
    var total: Double = 0.0
    for (resena in resenasPublicas) {
        total += resena.nota
    }
    val valoracion =
        if (resenasPublicas.isNotEmpty()) {
            resenasPublicas.map { it.nota }.average()
        } else {
            0.0
        }
    LazyColumn(modifier = modifier) {
        item {
            HeroPelicula(
                detalleTMDB!!.poster_path,
                detalleTMDB.title,
                onBack,
                detalleTMDB.title,
                cargando != false,
            )
            InformacionPrincipalPelicula(
                detalleTMDB.title,
                detalleTMDB.genres?.map { it.name } ?: emptyList(),
                detalleTMDB.vote_average,
                detalleTMDB.vote_count,
                valoracion,
                resenasPublicas.size,
                detalleTMDB.release_date,
            )
            TituloTexto("SINOPSIS", detalleTMDB.overview)
            DistribuidoresPelicula(watchProviders)
            ResenasPelicula(resenasPublicas, onResenasPublicas)
            FormularioResenaRapida(
                notaSlider,
                onNotaChange = onNotaChange,
                opinion,
                onOpinionChange = onOpinionChange,
                cargando != true,
            ) {
                onGuardar()
            }
        }
    }
}
