package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.ui.components.pelicula.PeliculaCard

@Composable
fun UltimaResenaCard(
    resena: Resena?,
    cargando: Boolean?,
) {
    Column(verticalArrangement = spacedBy(8.dp)) {
        PeliculaCard(resena!!, cargando) {}
        if (!resena.opinion.isNullOrEmpty()) {
            Text(text = resena.opinion, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
