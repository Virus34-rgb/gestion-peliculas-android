package com.angel.gestion_peliculas.ui.components.auth

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun Contrasena(pass: String, onValueChange: (String) -> Unit, label: String,
               cargando: Boolean?){
    var mostrarPass by remember { mutableStateOf(false) }
    OutlinedTextField(
        enabled = cargando != true,
        value = pass,
        onValueChange = onValueChange,
        trailingIcon = {
            IconButton(onClick = { mostrarPass = !mostrarPass }) {
                Icon(
                    imageVector = if (mostrarPass) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                    contentDescription = if (mostrarPass) "Ocultar contraseña" else "Mostrar contraseña"
                )
            }
        },
        label = { Text(label) },
        visualTransformation =
            if(mostrarPass) VisualTransformation.None
            else PasswordVisualTransformation()
    )
}