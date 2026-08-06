package com.angel.gestionpeliculas.ui.components.pelicula

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.ui.components.pelicula.ticket.TicketPelicula
import com.angel.gestionpeliculas.ui.theme.TicketColors

val TicketBorderWidth = 12.dp
val TicketOuterPadding = 8.dp

@Composable
fun PeliculaCard(
    resena: Resena,
    cargando: Boolean?,
    onClick: () -> Unit,
) {
    TicketPelicula(resena,cargando,onClick)
}




@Composable
private fun TicketBarcode(modifier: Modifier = Modifier) {

}
