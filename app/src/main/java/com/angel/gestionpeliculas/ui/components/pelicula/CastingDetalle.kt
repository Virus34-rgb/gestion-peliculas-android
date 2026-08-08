package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.TMDBActor
import com.angel.gestionpeliculas.ui.components.common.ImagenTexto

@Composable
fun CastingDetalle(actores: List<TMDBActor>) {
    Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        Text(
            text = "REPARTO PRINCIPAL",
            style = MaterialTheme.typography.titleSmall,
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(actores) { actor ->
                ImagenTexto(
                    actor.profilePath,
                    "w185",
                    actor.name,
                    Modifier,
                    actor.character,
                )
            }
        }
    }
}
