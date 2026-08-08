package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.angel.gestionpeliculas.model.getTrailer
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
            param.detalleTMDB?.let { p ->
                HeroPelicula(
                    p.posterPath,
                    param.onBack,
                    p.title,
                )
                InformacionPrincipalPelicula(
                    InformaconPrincipalPeliculaData(
                        p.title,
                        p.genres?.map { it.name } ?: emptyList(),
                        p.voteAverage,
                        p.voteCount,
                        valoracion,
                        param.resenasPublicas.size,
                        p.releaseDate,
                        p.runtime,
                    ),
                )
                val trailer = getTrailer(param.video)
                Trailer(trailer?.key, trailer?.name)
                TituloTexto("SINOPSIS", param.detalleTMDB.overview)
                param.credits?.let { c ->
                    val director = c.crew.firstOrNull { it.job == "Director" }
                    director?.let { d ->
                        DirectorDetalle(d)
                    }
                    val casting = c.cast.sortedBy { it.order }.take(10)
                    CastingDetalle(casting)
                }
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
                param.collection?.let { p ->
                    PeliculasTexto("MÁS PELICULAS DE LA SAGA", p.parts, param.navController)
                }
                param.similar?.let { p ->
                    PeliculasTexto("PELICULAS SIMILARES", p.results, param.navController)
                }
            }
        }
    }
}
