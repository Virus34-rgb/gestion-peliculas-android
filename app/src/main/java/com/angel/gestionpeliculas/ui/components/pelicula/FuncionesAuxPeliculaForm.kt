package com.angel.gestionpeliculas.ui.components.pelicula

import com.angel.gestionpeliculas.model.ResenaRequest
import com.angel.gestionpeliculas.model.TMDBGenres
import com.angel.gestionpeliculas.model.parametros.CrearResenaRequestData
import com.angel.gestionpeliculas.model.parametros.SeleccionarPeliculaTMDBData
import java.time.LocalDate

fun seleccionarPeliculaTMDB(param: SeleccionarPeliculaTMDBData) {
    val pelicula = param.pelicula
    param.onNombre(pelicula.title)
    param.onFecha(
        try {
            LocalDate.parse(pelicula.releaseDate)
        } catch (_: Exception) {
            null
        },
    )
    param.onPoster(pelicula.posterPath)
    param.onGeneros(
        pelicula.genreIds.mapNotNull {
            TMDBGenres.genres[it.toInt()]
        },
    )
    param.onTmdbId(pelicula.id)
}

fun crearResenaRequest(p: CrearResenaRequestData): ResenaRequest =
    ResenaRequest(p.tmdbId, p.nombre, ArrayList(p.generos), p.fecha, p.posterPath, p.nota, p.opinion)
