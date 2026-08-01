package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.angel.gestionpeliculas.R

@Composable
fun ImagenPath(
    logoPath: String?,
    nombre: String,
    modifier: Modifier,
) {
    AsyncImage(
        model =
            if (logoPath.isNullOrEmpty()) {
                null
            } else {
                "https://image.tmdb.org/t/p/w92$logoPath"
            },
        contentDescription = nombre,
        placeholder = painterResource(R.drawable.placeholder),
        error = painterResource(R.drawable.ic_no_poster),
        fallback = painterResource(R.drawable.ic_no_poster),
        modifier = modifier,
        contentScale = ContentScale.Fit,
    )
}
