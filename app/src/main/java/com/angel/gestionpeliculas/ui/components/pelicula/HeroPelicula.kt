package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun HeroPelicula(
    posterPath: String?,
    titulo: String,
    onBack: () -> Unit,
    nombre: String,
    habilitado: Boolean,
) {
    Box(modifier = Modifier.fillMaxWidth().height(300.dp), contentAlignment = Alignment.TopCenter) {
        ImagenPath(posterPath, nombre, Modifier.fillMaxWidth().height(300.dp))
        Box(
            modifier =
                Modifier
                    .matchParentSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops =
                                arrayOf(
                                    0.0f to Color.Transparent,
                                    0.45f to Color.Transparent,
                                    0.70f to MaterialTheme.colorScheme.surface.copy(alpha = 0.25f),
                                    0.85f to MaterialTheme.colorScheme.surface.copy(alpha = 0.75f),
                                    1.0f to MaterialTheme.colorScheme.surface,
                                ),
                        ),
                    ),
        )
        IconButton(
            onClick = onBack,
            modifier =
                Modifier
                    .align(Alignment.TopStart)
                    .padding(16.dp)
                    .size(44.dp)
                    .background(Color.Black.copy(alpha = 0.45f), CircleShape),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Volver",
                tint = Color.White,
            )
        }
    }
}
