package com.angel.gestionpeliculas.ui.components.pelicula

import com.angel.gestionpeliculas.model.ResenaRequest
import com.angel.gestionpeliculas.model.TMDBGenres
import com.angel.gestionpeliculas.model.TMDBMovie
import java.time.LocalDate

fun seleccionarPeliculaTMDB(
    pelicula: TMDBMovie,
    onNombre: (String) -> Unit,
    onFecha: (LocalDate?) -> Unit,
    onPoster: (String?) -> Unit,
    onGeneros: (List<String>) -> Unit,
    onTmdbId: (Long?) -> Unit,
) {
    onNombre(pelicula.title)
    onFecha(
        try {
            LocalDate.parse(pelicula.release_date)
        } catch (_: Exception) {
            null
        },
    )
    onPoster(pelicula.poster_path)
    onGeneros(
        pelicula.genre_ids.mapNotNull {
            TMDBGenres.genres[it.toInt()]
        },
    )
    onTmdbId(pelicula.id)
}

fun crearResenaRequest(
    tmdbId: Long?,
    nombre: String,
    generos: List<String>,
    fecha: LocalDate,
    posterPath: String?,
    nota: Double,
    opinion: String?,
): ResenaRequest = ResenaRequest(tmdbId, nombre, ArrayList(generos), fecha!!, posterPath, nota, opinion)
