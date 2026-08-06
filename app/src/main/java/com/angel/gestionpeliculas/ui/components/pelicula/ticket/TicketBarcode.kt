package com.angel.gestionpeliculas.ui.components.pelicula.ticket

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.ui.theme.TicketColors

@Composable
fun TicketBarcode(modifier : Modifier) {
    val color = TicketColors.Separator
    Canvas(modifier = modifier) {
        val grosores = listOf(
            2.dp,
            4.dp,
            1.dp,
            3.dp,
            2.dp,
            5.dp,
            1.dp,
            3.dp,
            4.dp,
            2.dp,
            1.dp,
            5.dp,
            3.dp,
            2.dp,
        )
        val espacio = 2.dp.toPx()
        var y = 0f
        grosores.forEach { grosor ->
            val alturaBarra = grosor.toPx()
            drawRect(
                color = color,
                topLeft = Offset(x = 0f, y = y),
                size = Size(width = size.width, height = alturaBarra),
            )
            y += alturaBarra + espacio
        }
    }
}