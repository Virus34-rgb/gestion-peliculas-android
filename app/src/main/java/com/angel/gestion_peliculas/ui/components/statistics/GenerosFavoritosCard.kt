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
import com.angel.gestion_peliculas.model.GeneroData

@Composable
fun GenerosFavoritosCard(lista: List<GeneroData>) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(verticalArrangement = spacedBy(8.dp), modifier = Modifier.padding(8.dp)) {
            for (item: GeneroData in lista) {
                GenerosItem(item.genero, item.cantidad)
            }
        }
    }
}

@Composable
private fun GenerosItem(genero: String, numero: Int) {
    Row(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "${genero} -> ${numero}",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}