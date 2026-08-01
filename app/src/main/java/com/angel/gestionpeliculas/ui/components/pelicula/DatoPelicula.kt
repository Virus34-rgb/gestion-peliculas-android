package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow

@Composable
fun DatoPelicula(
    titulo: String,
    dato: String,
) {
    Text(
        text = "$titulo        $dato",
        style = MaterialTheme.typography.bodyMedium,
        maxLines = 2,
        minLines = 2,
        overflow = TextOverflow.Ellipsis,
    )
}
