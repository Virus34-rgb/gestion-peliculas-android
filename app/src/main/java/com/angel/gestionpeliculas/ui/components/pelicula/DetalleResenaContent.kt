package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.ui.components.common.ConfirmDeleteDialog

@Composable
fun DetalleResenaContent(
    resena: Resena?,
    cargando: Boolean,
    mostrarDialogo: Boolean,
    onConfirmarBorrado: () -> Unit,
    onCancelarBorrado: () -> Unit,
    onBorrar: () -> Unit,
    onActualizar: () -> Unit,
    onBack: () -> Unit,
    modifier: Modifier,
) {
    if (mostrarDialogo) {
        ConfirmDeleteDialog(
            "Confirmar Borrado",
            "Estas apunto de borrar una reseña,estas seguro de querer hacerlo?",
            onConfirmarBorrado,
            onCancelarBorrado,
        )
    }
    Column(modifier = modifier) {
        resena?.let { r ->
            HeroPelicula(r.posterPath, r.nombre, onBack, r.nombre, cargando)
            InformacionPrincipalResena(r.nombre, r.generos, r.nota, r.fecha.toString())
            TituloTexto("MI OPINIÓN", r.opinion)
            AccionesResena(
                enabled = !cargando,
                onBorrar = onBorrar,
                onActualizar = onActualizar,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
