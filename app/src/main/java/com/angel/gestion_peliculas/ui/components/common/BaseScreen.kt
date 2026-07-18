package com.angel.gestion_peliculas.ui.components.common

import androidx.compose.runtime.Composable

@Composable
fun BaseScreen(
    cargando: Boolean?,
    error: String?,
    onErrorReset: () -> Unit,
    content: @Composable () -> Unit
) {
    ScreenError(cargando, error, onErrorReset)
    ScreenLoading(cargando)
    if (cargando == false) {
        content()
    }
}