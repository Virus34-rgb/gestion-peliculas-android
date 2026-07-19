package com.angel.gestion_peliculas.ui.components.common

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun ScreenError(cargando: Boolean?, error: String?, reset: () -> Unit) {
    val context = LocalContext.current
    ErrorPopup(cargando, error, context, reset)
}