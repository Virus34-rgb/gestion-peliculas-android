package com.angel.gestionpeliculas.ui.components.pelicula.ticket

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.ui.theme.TicketColors

@Composable
fun TicketBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Box(
        modifier =
            modifier.background(
                brush = Brush.verticalGradient(colors = listOf(TicketColors.Background, TicketColors.BackgroundDark)),
                shape = RoundedCornerShape(16.dp),
            ),
    ) {
        content()
    }
}
