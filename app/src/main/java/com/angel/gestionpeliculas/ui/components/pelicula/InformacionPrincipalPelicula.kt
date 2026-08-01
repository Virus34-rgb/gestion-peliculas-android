package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun InformacionPrincipalPelicula(
    titulo: String,
    generos: List<String>,
    valoracionTMDB: Double,
    numVotosTMDB: Int,
    valoracion: Double,
    numResenas: Int,
    fecha: String,
) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            for (genero in generos) {
                GenreChip(genero)
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ValoracionResumen("TMDB", valoracionTMDB, numVotosTMDB, "votos")
            if (numResenas > 0) {
                ValoracionResumen("Mi colección", valoracion, numResenas, "reseñas")
            }
        }
        DatoPelicula("Fecha de estreno", fecha.toString())
    }
}
