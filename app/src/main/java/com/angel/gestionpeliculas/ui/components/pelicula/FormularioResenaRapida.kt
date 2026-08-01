package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.ui.components.common.BotonPrincipal

@Composable
fun FormularioResenaRapida(
    nota: Float,
    onNotaChange: (Float) -> Unit,
    opinion: String?,
    onOpinionChange: (String) -> Unit,
    enabled: Boolean,
    onGuardar: () -> Unit,
) {
    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "MI VALORACIÓN",
            style = MaterialTheme.typography.titleSmall,
        )
        NotaOpinionEditor(nota, onNotaChange, opinion, onOpinionChange, enabled)
        BotonPrincipal(
            "Añadir/Actualizar Reseña",
            enabled,
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            onGuardar()
        }
    }
}
