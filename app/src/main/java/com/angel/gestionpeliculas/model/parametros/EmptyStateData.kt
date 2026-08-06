package com.angel.gestionpeliculas.model.parametros

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

data class EmptyStateData(
    val icono: ImageVector,
    val titulo: String,
    val descripcion: String? = null,
    val textoBoton: String? = null,
    val onClickBoton: (() -> Unit)? = null,
    val modifier: Modifier = Modifier,
)
