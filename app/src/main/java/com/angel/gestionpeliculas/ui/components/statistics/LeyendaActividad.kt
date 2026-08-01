package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun LeyendaActividad() {
    Column(
        modifier =
            Modifier
                .padding(4.dp),
    ) {
        val primary = MaterialTheme.colorScheme.primary
        LeyendaActividadItem("0 reseñas", MaterialTheme.colorScheme.surfaceVariant)
        LeyendaActividadItem("1 reseña", primary.copy(alpha = 0.35f))
        LeyendaActividadItem("2 reseñas", primary.copy(alpha = 0.65f))
        LeyendaActividadItem(">2 reseñas", primary)
    }
}

@Composable
fun LeyendaActividadItem(
    texto: String,
    color: Color,
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Canvas(Modifier.size(5.dp)) {
            drawCircle(
                color = color,
                radius = 5.dp.toPx(),
            )
        }
        Spacer(Modifier.size(8.dp))
        Text(
            text = texto,
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.weight(1f),
        )
    }
}
