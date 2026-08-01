package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.ActivityDayData

@Composable
fun ActivityWeekRow(
    lista: List<ActivityDayData>,
    modifier: Modifier = Modifier,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(4.dp), modifier = modifier) {
        for (elemento in lista) {
            if (!elemento.pertenece) {
                Box(
                    Modifier
                        .size(12.dp)
                        .background(color = Color.Transparent, shape = RoundedCornerShape(4.dp)),
                ) {
                }
            } else {
                ActivityDay(elemento.intensidad)
            }
        }
    }
}
