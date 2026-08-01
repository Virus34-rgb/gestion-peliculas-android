package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ActivityHeader(
    mes: YearMonth,
    onClickIzq: () -> Unit,
    onClickDer: () -> Unit,
    limiteSup: YearMonth?,
    limiteInf: YearMonth?,
) {
    val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale("es", "ES"))
    Row {
        IconButton(onClick = onClickIzq, enabled = mes != limiteInf) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Opciones")
        }
        Text(
            text = mes.format(formatter),
            style = MaterialTheme.typography.titleSmall,
        )
        IconButton(onClick = onClickDer, enabled = mes != limiteSup) {
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Opciones")
        }
    }
}
