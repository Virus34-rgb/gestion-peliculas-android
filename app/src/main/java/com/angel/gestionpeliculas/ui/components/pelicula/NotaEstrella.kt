package com.angel.gestionpeliculas.ui.components.pelicula

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@SuppressLint("DefaultLocale")
@Composable
fun NotaEstrella(
    nota: Double,
    size: Dp = 45.dp,
    modifier: Modifier = Modifier,
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        var notaE = nota / 2
        for (i in 0 until 5) {
            val porcentaje = notaE.coerceIn(0.0, 1.0).toFloat()
            EstrellaParcial(porcentaje, size)
            notaE -= 1.0
        }
        val notaTexto =
            if (nota % 1.0 == 0.0) {
                nota.toInt().toString()
            } else {
                String.format("%.1f", nota)
            }
        Spacer(Modifier.size(8.dp))
        Text(
            text = notaTexto,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

@Composable
fun EstrellaParcial(
    porcentaje: Float,
    iconSize: Dp,
) {
    Box(modifier = Modifier.size(iconSize)) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color.Gray,
            modifier = Modifier.matchParentSize(),
        )
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color(0xFFF4E5A1),
            modifier =
                Modifier
                    .matchParentSize()
                    .drawWithContent {
                        clipRect(right = size.width * porcentaje) {
                            this@drawWithContent.drawContent()
                        }
                    },
        )
    }
}
