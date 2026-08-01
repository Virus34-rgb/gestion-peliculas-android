package com.angel.gestionpeliculas.ui.components.common

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import kotlinx.coroutines.delay

@Composable
fun ScreenLoading(cargando: Boolean?) {
    var esperaLarga by remember { mutableStateOf(false) }
    LaunchedEffect(cargando) {
        if (cargando == true) {
            esperaLarga = false
            delay(5000)
            esperaLarga = true
        } else {
            esperaLarga = false
        }
    }
    CargandoCirculo(cargando, esperaLarga)
}
