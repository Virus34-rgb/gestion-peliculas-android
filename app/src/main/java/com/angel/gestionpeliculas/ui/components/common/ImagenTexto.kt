package com.angel.gestionpeliculas.ui.components.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.angel.gestionpeliculas.R

@Composable
fun ImagenTexto(
    path: String?,
    codigo: String,
    texto1: String,
    modifier: Modifier = Modifier,
    texto2: String? = null,
) {
    Column(verticalArrangement = Arrangement.Center, modifier = Modifier.padding(4.dp)) {
        AsyncImage(
            model =
                if (path.isNullOrEmpty()) {
                    null
                } else {
                    aTMDBImage(path, codigo)
                },
            contentDescription = null,
            placeholder = painterResource(R.drawable.placeholder),
            error = painterResource(R.drawable.ic_no_poster),
            fallback = painterResource(R.drawable.ic_no_poster),
            modifier = modifier,
            contentScale = ContentScale.Fit,
        )
        Text(
            text = texto1,
            style = MaterialTheme.typography.bodyMedium,
        )
        if (texto2 != null) {
            Text(
                text = texto2,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
