package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotaOpinionEditor(
    nota: Float, onNotaChange: (Float) -> Unit, opinion: String?,
    onOpinionChange: (String) -> Unit, enabled: Boolean
) {
    Text(text = "Nota: ${"%.1f".format(nota)}", style = MaterialTheme.typography.titleMedium)
    Slider(
        enabled = enabled,
        value = nota,
        onValueChange = onNotaChange,
        valueRange = 0f..10f,
        steps = 19
    )
    OutlinedTextField(
        enabled = enabled,
        value = opinion ?: "",
        onValueChange = onOpinionChange,
        label = { Text("Opinión (opcional)") },
        modifier = Modifier.fillMaxWidth(),
        minLines = 3
    )
}