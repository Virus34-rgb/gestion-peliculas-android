package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.EvolucionData

@Composable
fun EvolutionCard(lista: List<EvolucionData>?) {
    Column(modifier = Modifier.padding(4.dp)) {
        Text(text = "Evolución Reseñas", style = MaterialTheme.typography.bodyMedium)
        EvolucionResenasChart(lista)
        Text(text = "Evolución Notas", style = MaterialTheme.typography.bodyMedium)
        EvolucionNotasChart(lista)
    }
}
