package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.GeneroData
import com.angel.gestionpeliculas.ui.theme.TMDBGenreColors

@Composable
fun GenerosFavoritosCard(lista: List<GeneroData>?) {
    val listaOrdenada = lista!!.sortedByDescending { it.cantidad }
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
    ) {
        var total: Int = 0
        for (genero in listaOrdenada) {
            total += genero.cantidad
        }
        Spacer(Modifier.size(16.dp))
        Canvas(Modifier.size(90.dp).align(Alignment.CenterHorizontally)) {
            var startAngle = -90f
            for (genero in listaOrdenada) {
                var porcentaje = genero.cantidad.toFloat() / total.toFloat() * 360f
                val grosor = 24.dp.toPx()
                val margen = grosor / 2
                drawArc(
                    color = TMDBGenreColors.colors[genero.genero] ?: TMDBGenreColors.defaultColor,
                    startAngle = startAngle,
                    sweepAngle = porcentaje - 2f,
                    useCenter = false,
                    topLeft = Offset(margen, margen),
                    size =
                        Size(
                            width = size.width - 2 * margen,
                            height = size.height - 2 * margen,
                        ),
                    style = Stroke(width = grosor),
                )
                startAngle += porcentaje
            }
        }
        Column(Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
            for (genero in listaOrdenada) {
                GenerosItem(genero.genero, genero.cantidad)
            }
        }
    }
}

@Composable
private fun GenerosItem(
    genero: String,
    numero: Int,
) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Canvas(Modifier.size(10.dp)) {
            drawCircle(
                color = TMDBGenreColors.colors[genero] ?: TMDBGenreColors.defaultColor,
                radius = 10.dp.toPx(),
            )
        }
        Spacer(Modifier.size(8.dp))
        Text(
            text = "$genero ",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "$numero ",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
