package com.angel.gestion_peliculas.ui.components.statistics

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestion_peliculas.model.DistribucionData

@Composable
fun DistribucionNotasCard(distribucion: List<DistribucionData>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(verticalArrangement = spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
            for (item: DistribucionData in distribucion) {
                DistribucionItem(item.intervalo, item.numero)
            }
        }
    }
}

@Composable
private fun DistribucionItem(intervalo: String, numero: Int) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = intervalo,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f)
        )
        Text(text = numero.toString(), style = MaterialTheme.typography.bodyMedium)
    }
}