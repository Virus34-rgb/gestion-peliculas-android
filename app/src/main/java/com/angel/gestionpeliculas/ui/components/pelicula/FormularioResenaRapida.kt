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
import com.angel.gestionpeliculas.model.parametros.FormularioResenaRapidaData
import com.angel.gestionpeliculas.ui.components.common.BotonPrincipal

@Composable
fun FormularioResenaRapida(param: FormularioResenaRapidaData) {
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
        NotaOpinionEditor(param.nota, param.onNotaChange, param.opinion, param.onOpinionChange, param.enabled)
        BotonPrincipal(
            "Añadir/Actualizar Reseña",
            param.enabled,
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            param.onGuardar()
        }
    }
}
