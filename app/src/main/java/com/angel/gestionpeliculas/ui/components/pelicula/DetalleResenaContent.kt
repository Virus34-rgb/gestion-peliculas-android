package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.parametros.DetalleResenaContentData
import com.angel.gestionpeliculas.ui.components.common.ConfirmDeleteDialog

@Composable
fun DetalleResenaContent(param: DetalleResenaContentData) {
    if (param.mostrarDialogo) {
        ConfirmDeleteDialog(
            "Confirmar Borrado",
            "Estas apunto de borrar una reseña,estas seguro de querer hacerlo?",
            param.onConfirmarBorrado,
            param.onCancelarBorrado,
        )
    }
    Column(modifier = param.modifier) {
        param.resena?.let { r ->
            HeroPelicula(r.posterPath, param.onBack, r.nombre)
            InformacionPrincipalResena(r.nombre, r.generos, r.nota, r.fecha.toString())
            TituloTexto("MI OPINIÓN", r.opinion)
            AccionesResena(
                enabled = !param.cargando,
                onBorrar = param.onBorrar,
                onActualizar = param.onActualizar,
                modifier = Modifier.padding(16.dp),
            )
        }
    }
}
