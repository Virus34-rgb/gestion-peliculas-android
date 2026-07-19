package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestion_peliculas.model.ResenaPublicaResponse

@Composable
fun SeccionResenasPublicas(resenasPublicas: List<ResenaPublicaResponse>, onClick: () -> Unit) {
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp)) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Reseñas de usuarios: ",
                style = MaterialTheme.typography.titleMedium
            )
            LazyRow() {
                items(resenasPublicas.take(5)) { resena ->
                    ResenaPublicaCard(resena)
                }
            }
            TextButton(
                enabled = resenasPublicas.size > 5,
                onClick = onClick
            ) {
                Text(text = "Ver el resto")
            }
        }
    }
}