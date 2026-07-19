package com.angel.gestion_peliculas.ui.components.pelicula

import com.angel.gestion_peliculas.model.ResenaRequest
import com.angel.gestion_peliculas.model.TMDBGenres
import com.angel.gestion_peliculas.model.TMDBMovie
import java.time.LocalDate

fun seleccionarPeliculaTMDB(
    pelicula: TMDBMovie, onNombre: (String) -> Unit,
    onFecha: (LocalDate?) -> Unit, onPoster: (String?) -> Unit,
    onGeneros: (List<String>) -> Unit, onTmdbId: (Long?) -> Unit
) {
    onNombre(pelicula.title)
    onFecha(
        try {
            LocalDate.parse(pelicula.release_date)
        } catch (_: Exception) {
            null
        }
    )
    onPoster(pelicula.poster_path)
    onGeneros(
        pelicula.genre_ids.mapNotNull {
            TMDBGenres.genres[it.toInt()]
        }
    )
    onTmdbId(pelicula.id)
}

fun crearResenaRequest(
    tmdbId: Long?, nombre: String, generos: List<String>, fecha: LocalDate,
    posterPath: String?, nota: Double, opinion: String?
): ResenaRequest {
    return ResenaRequest(tmdbId, nombre, ArrayList(generos), fecha!!, posterPath, nota, opinion)
}