package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.ui.components.pelicula.ticket.TicketPelicula

val TicketBorderWidth = 12.dp
val TicketOuterPadding = 8.dp

@Composable
fun PeliculaCard(
    resena: Resena,
    cargando: Boolean?,
    onClick: () -> Unit,
) {
    TicketPelicula(resena, cargando, onClick)
}
