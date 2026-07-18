package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectorGeneros(seleccionados: SnapshotStateList<String>, disponibles: List<String>, enabled: Boolean,
                    isError: Boolean = false, mensajeError: String? = null){
    var showBottomSheet by remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            enabled = enabled,
            modifier = Modifier.weight(1f),
            value = if (seleccionados.isEmpty()) "Sin generos seleccionados" else seleccionados.joinToString(", "),
            onValueChange = { },
            isError = isError,
            supportingText = {
                if (isError && mensajeError != null) {
                    Text(mensajeError)
                }
            },
            label = { Text("Generos") },
            readOnly = true
        )
        Button(
            enabled = enabled,
            onClick = { showBottomSheet = true },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Icon(
                imageVector = Icons.Default.AddBox,
                contentDescription = "Seleccionar generos"
            )
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false }
        ) {
            LazyColumn {
                items(disponibles) { genero ->
                    Row {
                        Checkbox(
                            checked = seleccionados.contains(genero),
                            onCheckedChange = { checked ->
                                if (checked) seleccionados.add(genero)
                                else seleccionados.remove(genero)
                            }
                        )
                        Text(genero)
                    }
                }
            }
        }
    }
}