package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angel.gestionpeliculas.model.RachaData

@Composable
fun RachaCard(racha: RachaData?) {
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        val contenidoExtra1 = textoSemanas(racha?.rachaActual ?: 0)
        val contenidoExtra2 = textoSemanas(racha?.rachaMaxima ?: 0)
        RachaItem("\uD83D\uDD25 Racha Actual", racha?.rachaActual.toString() + " " + contenidoExtra1)
        RachaItem("\uD83C\uDFC6 Mejor Racha", racha?.rachaMaxima.toString() + " " + contenidoExtra2)
    }
}

@Composable
private fun RachaItem(
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

private fun textoSemanas(valor: Int) = if (valor == 1) "semana" else "semanas"
