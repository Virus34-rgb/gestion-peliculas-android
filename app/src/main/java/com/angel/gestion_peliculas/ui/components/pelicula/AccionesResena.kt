package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestion_peliculas.ui.components.common.BotonPrincipal

@Composable
fun AccionesResena(enabled: Boolean, onBorrar: () -> Unit,
                   onActualizar: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        BotonPrincipal(
            texto = "Borrar reseña", enabled, modifier = Modifier.weight(1f)
        ) { onBorrar() }

        BotonPrincipal(
            texto = "Actualizar reseña", enabled, modifier = Modifier.weight(1f)
        ) { onActualizar() }
    }
}