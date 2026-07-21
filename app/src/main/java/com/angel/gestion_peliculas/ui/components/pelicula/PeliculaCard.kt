package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.angel.gestion_peliculas.model.Resena
import com.angel.gestion_peliculas.ui.theme.TicketColors

private val TicketBorderWidth = 12.dp
private val TicketOuterPadding = 8.dp

@Composable
fun PeliculaCard(resena: Resena, cargando: Boolean?, onClick: () -> Unit) {
    Box {
        TicketBorder(Modifier.matchParentSize())
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(TicketBorderWidth)
                .clickable(enabled = cargando != true)
                { onClick() },
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.elevatedCardColors(containerColor = TicketColors.Background)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                LeftSection(resena, Modifier
                    .weight(1f)
                    .padding(16.dp))
                TicketSeparator(
                    modifier = Modifier
                        .height(70.dp)
                        .align(Alignment.CenterVertically)
                )
                RightSection(resena, Modifier
                    .width(50.dp)
                    .height(90.dp))
            }
        }
    }
}

@Composable
private fun LeftSection(resena: Resena, modifier: Modifier = Modifier) {
    Row(horizontalArrangement = spacedBy(8.dp), modifier = modifier) {
        ImagenPath(
            resena.posterPath, resena.nombre, Modifier
                .width(60.dp)
                .height(90.dp)
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Text(
                text = resena.nombre,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = TicketColors.Title
            )
            Text(
                text = resena.generos.joinToString(", "),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                color = TicketColors.Body
            )
            Text(
                text = resena.fecha.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = TicketColors.Body
            )
        }
    }
}

@Composable
private fun RightSection(resena: Resena, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        NotaEstrella(resena.nota, 35.dp, Modifier.align(Alignment.TopEnd))
        TicketBarcode(Modifier
            .width(40.dp)
            .height(50.dp)
            .align(Alignment.BottomCenter))
    }
}

@Composable
private fun TicketSeparator(modifier: Modifier = Modifier) {
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
                strokeWidth = 2.dp.toPx()
            )
            currentOffset += longitudSegmento + espacioEntreSegmentos
        }
    }
}

@Composable
private fun TicketBorder(modifier: Modifier = Modifier) {
    val color = MaterialTheme.colorScheme.background
    val colorRectangulo = TicketColors.Separator
    Canvas(modifier = modifier) {
        val anchura = size.width
        val altura = size.height
        drawRoundRect(
            color = colorRectangulo,
            topLeft = Offset(TicketOuterPadding.toPx(), 0f),
            size = Size(anchura - 2 * TicketOuterPadding.toPx(), altura),
            cornerRadius = CornerRadius(16.dp.toPx())
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
                center = Offset(TicketOuterPadding.toPx(), y)
            )
            drawCircle(
                color = color,
                radius = radioLateral,
                center = Offset(anchura - TicketOuterPadding.toPx(), y)
            )
        }
    }
}

@Composable
private fun TicketBarcode(
    modifier: Modifier = Modifier
) {
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
            2.dp
        )
        val espacio = 2.dp.toPx()
        var y = 0f
        grosores.forEach { grosor ->
            val alturaBarra = grosor.toPx()
            drawRect(
                color = color,
                topLeft = Offset(x = 0f, y = y),
                size = Size(width = size.width, height = alturaBarra)
            )
            y += alturaBarra + espacio
        }
    }
}