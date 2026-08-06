package com.angel.gestionpeliculas.ui.components.pelicula.ticket

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.ui.theme.TicketColors

@Composable
fun TicketSeparator(modifier: Modifier) {
    val color = TicketColors.Separator
    Canvas(modifier = modifier) {
        val centro = size.width / 2
        val altura = size.height
        var currentOffset = 0f
        val longitudSegmento = 18f
        val espacioEntreSegmentos = 18f
        while (currentOffset < altura) {
            drawLine(
                color = color,
                start = Offset(x = centro, y = currentOffset),
                end = Offset(x = centro, y = currentOffset + longitudSegmento),
                strokeWidth = 2.dp.toPx(),
            )
            currentOffset += longitudSegmento + espacioEntreSegmentos
        }
    }
}
