package com.angel.gestion_peliculas.ui.components.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmDeleteDialog(
    titulo: String, mensaje: String, onConfirmar: () -> Unit, onCancelar: () -> Unit
) {
    AlertDialog(
        icon = {
        Icon(Icons.Default.Info, contentDescription = "")
    },
        title = {
            Text(titulo)
        },
        text = {
            Text(mensaje)
        },
        onDismissRequest = { onCancelar() },
        confirmButton = { TextButton(onClick = { onConfirmar() }) { Text("Confirmar") } },
        dismissButton = { TextButton(onClick = { onCancelar() }) { Text("Cancelar") } })
}