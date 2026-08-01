package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ActivityDay(intensidad: Int) {
    val primary = MaterialTheme.colorScheme.primary
    val colors =
        mapOf(
            0 to MaterialTheme.colorScheme.surfaceVariant,
            1 to primary.copy(alpha = 0.35f),
            2 to primary.copy(alpha = 0.65f),
            3 to primary,
        )
    val default = primary
    Box(
        Modifier
            .size(12.dp)
            .background(colors[intensidad] ?: default, shape = RoundedCornerShape(4.dp)),
    ) {
    }
}
