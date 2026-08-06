package com.angel.gestionpeliculas.ui.components.search

import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.parametros.BuscadorTMDBData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscadorTMDB(param: BuscadorTMDBData) {
    var expanded by remember { mutableStateOf(false) }
    val enabled = param.enabled
    val isError = param.isError
    val mensajeError = param.mensajeError
    val resultados = param.resultados
    ExposedDropdownMenuBox(
        expanded = expanded && param.resultados?.isNotEmpty() == true && enabled,
        onExpandedChange = { expanded = it },
    ) {
        OutlinedTextField(
            enabled = enabled,
            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            value = param.nombre,
            onValueChange = {
                param.onNombreChange(it)
                expanded = true
            },
            label = { Text("Nombre") },
            isError = isError,
            supportingText = {
                if (isError && mensajeError != null) {
                    Text(mensajeError)
                }
            },
        )
        ExposedDropdownMenu(
            expanded = expanded && resultados?.isNotEmpty() == true,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(max = 300.dp),
        ) {
            resultados?.forEach { pelicula ->
                DropdownMenuItem(
                    text = { Text(pelicula.title) },
                    onClick = {
                        param.onPeliculaSeleccionada(pelicula)
                        expanded = false
                    },
                )
            }
        }
    }
}
