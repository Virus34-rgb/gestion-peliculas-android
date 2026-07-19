package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angel.gestion_peliculas.model.Resena
import com.angel.gestion_peliculas.ui.components.common.ConfirmDeleteDialog

@Composable
fun DetalleResenaContent(
    resena: Resena?, cargando: Boolean, mostrarDialogo: Boolean,
    onConfirmarBorrado: () -> Unit, onCancelarBorrado: () -> Unit,
    onBorrar: () -> Unit, onActualizar: () -> Unit, modifier: Modifier
) {
    if (mostrarDialogo) {
        ConfirmDeleteDialog(
            "Confirmar Borrado",
            "Estas apunto de borrar una reseña,estas seguro de querer hacerlo?",
            onConfirmarBorrado,
            onCancelarBorrado
        )
    }
    Column(modifier = modifier) {
        CabeceraPelicula(
            resena!!.posterPath,
            resena.nombre,
            resena.nota,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
        InformacionPelicula(resena.generos, resena.fecha.toString()) {
            if (!resena.opinion.isNullOrBlank()) {
                Text(
                    text = "Opinión: ${resena.opinion}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        AccionesResena(
            enabled = cargando != true,
            onBorrar = onBorrar,
            onActualizar = { onActualizar() },
            modifier = Modifier.padding(16.dp)
        )
    }
}