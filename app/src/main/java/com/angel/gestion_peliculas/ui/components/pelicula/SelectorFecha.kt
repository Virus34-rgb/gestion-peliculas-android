package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

@Composable
fun SelectorFecha(fecha: LocalDate?, onFechaSeleccionada: (LocalDate) -> Unit, enabled: Boolean,
                  isError: Boolean = false, mensajeError: String? = null){
    val datePickerState = rememberDatePickerState()
    var showDatePicker by remember { mutableStateOf(false) }
    Row(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            enabled = enabled,
            modifier = Modifier.weight(1f),
            value = fecha?.toString() ?: "Sin fecha seleccionada",
            onValueChange = { },
            isError = isError,
            supportingText = {
                if (isError && mensajeError != null) {
                    Text(mensajeError)
                }
            },
            label = { Text("Fecha") },
            readOnly = true
        )
        Button(
            enabled = enabled,
            onClick = { showDatePicker = true },
            modifier = Modifier.padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Icon(
                imageVector = Icons.Default.DateRange,
                contentDescription = "Seleccionar fecha"
            )
        }
    }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(enabled = enabled,
                    onClick = {
                        showDatePicker = false
                        datePickerState.selectedDateMillis?.let { millis ->
                            val nuevaFecha = Instant.ofEpochMilli(millis)
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate()
                            onFechaSeleccionada(nuevaFecha)
                        }
                    }) { Text("OK") }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}