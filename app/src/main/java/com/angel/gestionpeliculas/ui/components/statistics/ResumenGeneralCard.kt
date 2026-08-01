package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.ResumenData

@Composable
fun ResumenGeneralCard(resumen: ResumenData?) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
    ) {
        Column(verticalArrangement = spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
            ) {
                ResumenItem("\uD83C\uDFAC", "Numero de Reseñas", resumen!!.numero.toString())
                val notaMedia = String.format("%.2f", resumen!!.notaMedia)
                ResumenItem("⭐", "Nota media", notaMedia.toString())
            }
            HorizontalDivider(thickness = 2.dp)
            ResumenItem("\uD83C\uDFC6 Mejor Pelicula", resumen!!.mejor)
            ResumenItem("\uD83D\uDC94 Peor Pelicula", resumen!!.peor)
        }
    }
}

@Composable
private fun ResumenItem(
    icon: String,
    titulo: String,
    contenido: String,
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = icon,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary,
        )
        Spacer(Modifier.size(4.dp))
        Text(text = contenido, style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.size(2.dp))
        Text(
            text = titulo,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }
}

@Composable
private fun ResumenItem(
    titulo: String,
    contenido: String,
) {
    Column(Modifier.fillMaxWidth()) {
        Text(
            text = titulo,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Text(text = contenido, style = MaterialTheme.typography.titleMedium)
    }
}
