package com.angel.gestionpeliculas.model.parametros

import androidx.compose.ui.Modifier
import com.angel.gestionpeliculas.model.Resena

data class DetalleResenaContentData(
    val resena: Resena?,
    val cargando: Boolean,
    val mostrarDialogo: Boolean,
    val onConfirmarBorrado: () -> Unit,
    val onCancelarBorrado: () -> Unit,
    val onBorrar: () -> Unit,
    val onActualizar: () -> Unit,
    val onBack: () -> Unit,
    val modifier: Modifier,
)
