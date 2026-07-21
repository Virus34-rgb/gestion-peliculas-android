package com.angel.gestion_peliculas.ui.components.statistics

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StatSection(titulo: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = spacedBy(8.dp)) {
        Text(text = titulo, style = MaterialTheme.typography.titleMedium)
        content()
    }
}