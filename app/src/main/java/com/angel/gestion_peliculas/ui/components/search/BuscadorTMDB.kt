package com.angel.gestion_peliculas.ui.components.search

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
import com.angel.gestion_peliculas.model.TMDBMovie

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuscadorTMDB(nombre: String, onNombreChange: (String) -> Unit, resultados: List<TMDBMovie>?,
                 onPeliculaSeleccionada: (TMDBMovie) -> Unit, enabled: Boolean, isError: Boolean = false,
                 mensajeError: String? = null){
    var expanded by remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        expanded = expanded && resultados?.isNotEmpty() == true && enabled,
        onExpandedChange = { expanded = it }
    ) {
        OutlinedTextField(
            enabled = enabled,
            modifier = Modifier.menuAnchor(ExposedDropdownMenuAnchorType.PrimaryEditable),
            value = nombre,
            onValueChange = { onNombreChange(it);expanded = true },
            label = { Text("Nombre") },
            isError = isError,
            supportingText = {
                if (isError && mensajeError != null) {
                    Text(mensajeError)
                }
            }
        )
        ExposedDropdownMenu(
            expanded = expanded && resultados?.isNotEmpty() == true,
            onDismissRequest = { expanded = false },
            modifier = Modifier.heightIn(max = 300.dp)
        ) {
            resultados?.forEach { pelicula ->
                DropdownMenuItem(
                    text = { Text(pelicula.title) },
                    onClick = {
                        onPeliculaSeleccionada(pelicula)
                        expanded = false
                    }
                )
            }
        }
    }
}