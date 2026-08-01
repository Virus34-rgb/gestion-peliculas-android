package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.EvolucionData

@Composable
fun EvolucionResenasChart(lista: List<EvolucionData>?) {
    if (lista.isNullOrEmpty()) return
    val color = MaterialTheme.colorScheme.primary
    Canvas(modifier = Modifier.fillMaxWidth().height(180.dp)) {
        val margen = 8.dp.toPx()
        val separacion = 4.dp.toPx()
        val anchoDisponible = size.width - margen * 2
        val numBarras = lista.size
        val anchoBarra =
            (anchoDisponible - separacion * (numBarras - 1)) / numBarras
        val margenInferior = 8.dp.toPx()
        val alturaUtil = size.height - margenInferior
        val yBase = size.height - margenInferior
        drawLine(
            color = color,
            start = Offset(margen, yBase),
            end = Offset(size.width - margen, yBase),
        )
        val valorMax = lista.maxOf { it.numeroResenas }
        var x = margen
        for (elemento in lista) {
            val proporcion =
                if (valorMax > 0) {
                    elemento.numeroResenas.toFloat() / valorMax.toFloat()
                } else {
                    0f
                }
            val altura = proporcion * alturaUtil
            drawRect(
                color = color,
                topLeft =
                    Offset(
                        x,
                        yBase - altura,
                    ),
                size =
                    androidx.compose.ui.geometry.Size(
                        anchoBarra,
                        altura,
                    ),
            )
            x += anchoBarra + separacion
        }
    }
}
