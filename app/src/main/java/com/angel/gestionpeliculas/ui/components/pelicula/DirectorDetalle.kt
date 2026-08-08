package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.TMDBCrew
import com.angel.gestionpeliculas.ui.components.common.ImagenTexto

@Composable
fun DirectorDetalle(d: TMDBCrew) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
    ) {
        Text(
            text = "DIRECTOR",
            style = MaterialTheme.typography.titleSmall,
        )
        ImagenTexto(
            d.profilePath,
            "w185",
            d.name,
            Modifier,
        )
    }
}
