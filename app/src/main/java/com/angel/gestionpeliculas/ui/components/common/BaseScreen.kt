package com.angel.gestionpeliculas.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable

@Composable
fun BaseScreen(
    cargando: Boolean?,
    error: String?,
    onErrorReset: () -> Unit,
    content: @Composable () -> Unit,
) {
    ScreenError(cargando, error, onErrorReset)
    Box {
        content()
        ScreenLoading(cargando)
    }
}
