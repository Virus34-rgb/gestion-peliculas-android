package com.angel.gestionpeliculas.ui.components.filters

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.ui.components.common.BotonOrden

@Composable
fun SortBar(
    cargando: Boolean?,
    onOrdenar: (String, Boolean) -> Unit,
) {
    var criterioActual by remember { mutableStateOf("") }
    var ascendente by remember { mutableStateOf(true) }
    val nombre = "Nombre"
    val nota = "Nota"
    val fecha = "Fecha"
    Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.padding(8.dp),
        ) {
            Text(text = "Ordenar por:", style = MaterialTheme.typography.labelLarge)
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth(),
            ) {
                BotonOrden(nombre, criterioActual.equals(nombre), ascendente) {
                    ascendente = if (criterioActual.equals(nombre)) !ascendente else true
                    criterioActual = nombre
                    onOrdenar("nombre", ascendente)
                }
                BotonOrden(nota, criterioActual.equals(nota), ascendente) {
                    ascendente = if (criterioActual.equals(nota)) !ascendente else true
                    criterioActual = nota
                    onOrdenar("nota", ascendente)
                }
                BotonOrden(fecha, criterioActual.equals(fecha), ascendente) {
                    ascendente = if (criterioActual.equals(fecha)) !ascendente else true
                    criterioActual = fecha
                    onOrdenar("fecha", ascendente)
                }
            }
        }
    }
}
