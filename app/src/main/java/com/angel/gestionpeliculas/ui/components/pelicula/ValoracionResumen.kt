package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ValoracionResumen(
    titulo: String,
    nota: Double,
    cantidad: Int,
    descripcionCantidad: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.padding(vertical = 4.dp),
    ) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyMedium,
        )
        NotaEstrella(nota, 30.dp, Modifier)
        Text(
            text = "$cantidad $descripcionCantidad",
            style = MaterialTheme.typography.bodySmall,
        )
    }
}
