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
import com.angel.gestionpeliculas.model.parametros.InformaconPrincipalPeliculaData

@Composable
fun InformacionPrincipalPelicula(param: InformaconPrincipalPeliculaData) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = param.titulo,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            for (genero in param.generos) {
                GenreChip(genero)
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ValoracionResumen(
                "TMDB",
                param.valoracionTMDB,
                param.numVotosTMDB,
                "votos",
            )
            if (param.numResenas > 0) {
                ValoracionResumen("Mi colección", param.valoracion, param.numResenas, "reseñas")
            }
        }
        param.fecha?.let { fecha ->
            DatoPelicula("Fecha de estreno", fecha)
        }
        param.runtime?.let { runtime ->
            DatoPelicula("Duracion", "$runtime min")
        }
    }
}
