package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun TituloTexto(
    titulo: String,
    texto: String?,
) {
    var expandida by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.titleSmall,
        )
        if (texto != null) {
            Text(
                text = texto,
                style = MaterialTheme.typography.bodySmall,
                maxLines = if (!expandida) 5 else Int.MAX_VALUE,
                overflow = TextOverflow.Ellipsis,
            )
        }
        TextButton(onClick = { expandida = !expandida }) {
            Text(
                text = if (!expandida) "Leer más" else "Leer menos",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
