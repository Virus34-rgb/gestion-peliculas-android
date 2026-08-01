package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.DistribucionData

@Composable
fun DistribucionNotasCard(distribucion: List<DistribucionData>?) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
    ) {
        Column(verticalArrangement = spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
            var total: Int = 0
            if (distribucion != null) {
                for (item: DistribucionData in distribucion) {
                    total += item.numero
                }
            }
            if (distribucion != null) {
                for (item: DistribucionData in distribucion) {
                    DistribucionItem(item.intervalo, item.numero, item.numero / total.toDouble())
                }
            }
        }
    }
}

@Composable
private fun DistribucionItem(
    intervalo: String,
    numero: Int,
    porcentaje: Double,
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = intervalo,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(48.dp),
        )
        Spacer(Modifier.width(8.dp))
        LinearProgressIndicator(
            progress = { porcentaje.toFloat() },
            modifier = Modifier.height(8.dp).weight(1f),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
            strokeCap = StrokeCap.Butt,
        )
        val porcentajeTexto = String.format("%.2f", porcentaje * 100)
        Spacer(Modifier.width(8.dp))
        Text(text = "$numero | $porcentajeTexto%", style = MaterialTheme.typography.bodyMedium, modifier = Modifier.width(96.dp))
    }
}
