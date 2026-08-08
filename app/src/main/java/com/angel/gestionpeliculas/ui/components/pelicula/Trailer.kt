package com.angel.gestionpeliculas.ui.components.pelicula

import android.content.Intent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage

@Composable
fun Trailer(
    youtubeKey: String?,
    nombre: String?,
) {
    val context = LocalContext.current
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(4.dp),
    ) {
        Text(
            text = "TRÁILER",
            style = MaterialTheme.typography.titleSmall,
        )
        if (youtubeKey == null) {
            Text(
                text = "No se ha encontrado un tráiler",
                style = MaterialTheme.typography.bodyMedium,
            )
        } else {
            AsyncImage(
                model = "https://img.youtube.com/vi/$youtubeKey/hqdefault.jpg",
                contentDescription = nombre,
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent =
                                Intent(
                                    Intent.ACTION_VIEW,
                                    "https://www.youtube.com/watch?v=$youtubeKey".toUri(),
                                )
                            context.startActivity(intent)
                        },
            )
        }
    }
}
