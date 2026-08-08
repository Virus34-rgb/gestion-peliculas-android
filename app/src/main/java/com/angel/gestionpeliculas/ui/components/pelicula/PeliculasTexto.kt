package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.ui.components.common.ImagenTexto

@Composable
fun PeliculasTexto(
    titulo: String,
    peliculas: List<TMDBMovie>,
    navController: NavController,
) {
    Column(
        modifier =
            Modifier
                .padding(8.dp),
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleSmall,
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(peliculas) { pelicula ->
                ImagenTexto(
                    pelicula.posterPath,
                    "w92",
                    pelicula.title,
                    Modifier
                        .width(90.dp)
                        .height(120.dp)
                        .clickable { navController.navigate("detalleCatalogo/${pelicula.id}") },
                )
            }
        }
    }
}
