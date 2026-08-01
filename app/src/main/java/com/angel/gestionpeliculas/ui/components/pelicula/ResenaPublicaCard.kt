package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.ResenaPublicaResponse

@Composable
fun ResenaPublicaCard(
    resena: ResenaPublicaResponse,
    compacta: Boolean = true,
) {
    Card(
        modifier =
            if (compacta) {
                Modifier.width(250.dp)
            } else {
                Modifier.fillMaxWidth()
            },
    ) {
        Column(
            modifier =
                Modifier.padding(
                    if (compacta) 12.dp else 20.dp,
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            if (compacta) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    UsuarioResena(resena.username)
                    NotaEstrella(resena.nota, 30.dp, Modifier)
                }
            } else {
                UsuarioResena(resena.username)
                NotaEstrella(
                    resena.nota,
                    40.dp,
                    Modifier,
                )
            }
            Text(
                text = resena.opinion ?: "Sin opinión",
                maxLines = if (compacta) 3 else Int.MAX_VALUE,
                overflow = TextOverflow.Ellipsis,
                style =
                    if (compacta) {
                        MaterialTheme.typography.bodySmall
                    } else {
                        MaterialTheme.typography.bodyMedium
                    },
            )
            Text(
                text = resena.fechaPublicacion.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
    }
}

@Composable
private fun UsuarioResena(username: String) {
    Text(
        text = username,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
    )
}
