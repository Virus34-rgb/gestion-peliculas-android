package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestion_peliculas.ui.components.common.BotonPrincipal

@Composable
fun FormularioResenaRapida(
    nota: Float,
    onNotaChange: (Float) -> Unit,
    opinion: String?,
    onOpinionChange: (String) -> Unit,
    enabled: Boolean,
    onGuardar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        NotaOpinionEditor(nota, onNotaChange, opinion, onOpinionChange, enabled)
        BotonPrincipal(
            "Añadir/Actualizar Reseña", enabled, Modifier.fillMaxWidth().padding(16.dp)
        ) {
            onGuardar()
        }
    }
}