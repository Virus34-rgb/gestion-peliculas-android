package com.angel.gestion_peliculas.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BotonOrden(texto : String, seleccionado : Boolean, ascendente : Boolean, onClick :() -> Unit){
    TextButton(onClick = onClick) {
        Row(horizontalArrangement = Arrangement.spacedBy(4.dp),verticalAlignment = Alignment.CenterVertically){
            Text(text = texto, style = MaterialTheme.typography.bodyMedium
                ,color = MaterialTheme.colorScheme.onSurfaceVariant)
            if (seleccionado && ascendente){
                Icon(imageVector = Icons.Default.ArrowUpward, contentDescription = "FlechaArriba", Modifier.size(16.dp),
                    MaterialTheme.colorScheme.primary)
            }
            else if (seleccionado && !ascendente){

                Icon(imageVector = Icons.Default.ArrowDownward, contentDescription = "FlechaAbajo", Modifier.size(16.dp),
                    MaterialTheme.colorScheme.primary)
            }
        }
    }
}