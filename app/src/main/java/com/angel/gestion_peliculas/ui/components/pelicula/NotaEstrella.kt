package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NotaEstrella (nota: Double, size: Dp = 60.dp, modifier: Modifier?){
    val colorSegunNota = when {
        nota >= 9 -> Color(0xFFF4E5A1)// dorado rosado
        nota >= 7 -> Color(0xFF4CAF50) // verde
        nota >= 5 -> Color(0xFFFFA726) // ámbar
        nota >= 2 -> Color(0xFFE53935) // rojo
        else -> Color(0xFF6A1B9A) // morado
    }
    Box(contentAlignment = Alignment.Center,modifier = Modifier) {
        // Estrella negra de fondo (el "borde")
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(size)
        )
        // Estrella de color, un poco más pequeña, encima
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = colorSegunNota,
            modifier = Modifier.size(size * 0.93f)
        )
        // El número de la nota, encima de todo, en negro para contraste
        val notaTexto = if (nota % 1.0 == 0.0) {
            nota.toInt().toString()
        } else {
            String.format("%.1f", nota)
        }
        Text(
            text = notaTexto,
            fontSize = (size.value * 0.3f).sp,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    }
}