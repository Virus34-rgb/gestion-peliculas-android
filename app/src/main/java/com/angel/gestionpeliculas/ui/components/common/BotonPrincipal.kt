package com.angel.gestionpeliculas.ui.components.common

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BotonPrincipal(
    texto: String,
    habilitado: Boolean,
    modifier: Modifier,
    onClick: () -> Unit,
) {
    Button(
        enabled = habilitado,
        onClick = onClick,
        modifier = modifier,
        colors =
            ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
    ) {
        Text(
            texto,
            style = MaterialTheme.typography.labelLarge,
        )
    }
}
