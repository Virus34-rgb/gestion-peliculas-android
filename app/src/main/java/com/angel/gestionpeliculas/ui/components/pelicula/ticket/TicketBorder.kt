package com.angel.gestionpeliculas.ui.components.pelicula.ticket

import androidx.compose.foundation.Canvas
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.ui.components.pelicula.TicketOuterPadding
import com.angel.gestionpeliculas.ui.theme.TicketColors

@Composable
fun TicketBorder(modifier : Modifier){
    val color = MaterialTheme.colorScheme.background
    val colorRectangulo = TicketColors.Separator
    Canvas(modifier = modifier) {
        val anchura = size.width
        val altura = size.height
        drawRoundRect(
            color = colorRectangulo,
            topLeft = Offset(TicketOuterPadding.toPx(), 0f),
            size = Size(anchura - 2 * TicketOuterPadding.toPx(), altura),
            cornerRadius = CornerRadius(16.dp.toPx()),
        )
        val radioPx = 12.dp.toPx()
        var offset = Offset(TicketOuterPadding.toPx() + radioPx * 0.25f, radioPx * 0.25f)
        drawCircle(color = color, radius = radioPx, center = offset)
        offset = Offset(anchura - TicketOuterPadding.toPx() - radioPx * 0.25f, radioPx * 0.25f)
        drawCircle(color = color, radius = radioPx, center = offset)
        offset = Offset(TicketOuterPadding.toPx() + radioPx * 0.25f, altura - radioPx * 0.25f)
        drawCircle(color = color, radius = radioPx, center = offset)
        offset =
            Offset(anchura - TicketOuterPadding.toPx() - radioPx * 0.25f, altura - radioPx * 0.25f)
        drawCircle(color = color, radius = radioPx, center = offset)
        val radioLateral = 5.dp.toPx()
        val margenVertical = 24.dp.toPx()
        val separacion = 12.dp.toPx()
        val espacioDisponible = altura - 2 * margenVertical
        val cantidad = (espacioDisponible / separacion).toInt()
        val espacioReal = if (cantidad > 1) {
            espacioDisponible / (cantidad - 1)
        } else {
            0f
        }
        val alturaMuescas = (cantidad - 1) * espacioReal
        val inicio = (altura - alturaMuescas) / 2
        repeat(cantidad) { i ->
            val y = inicio + i * espacioReal
            drawCircle(
                color = color,
                radius = radioLateral,
                center = Offset(TicketOuterPadding.toPx(), y),
            )
            drawCircle(
                color = color,
                radius = radioLateral,
                center = Offset(anchura - TicketOuterPadding.toPx(), y),
            )
        }
    }
}