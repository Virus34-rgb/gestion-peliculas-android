package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.angel.gestion_peliculas.R
import com.angel.gestion_peliculas.model.TMDBMovie

@Composable
fun PeliculaBusquedaCard(pelicula : TMDBMovie, onclick : () -> Unit,
                         cargando : Boolean?){

    Card (
        modifier = Modifier.fillMaxWidth().clickable(enabled = cargando != true)
        {onclick()},
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            AsyncImage(
                model = if (pelicula.poster_path.isNullOrBlank()) null
                else "https://image.tmdb.org/t/p/w500${pelicula.poster_path}",
                contentDescription = pelicula.title,
                contentScale = ContentScale.Crop,
                placeholder = painterResource(R.drawable.placeholder),
                error = painterResource(R.drawable.ic_no_poster),
                fallback = painterResource(R.drawable.ic_no_poster),
                modifier = Modifier.aspectRatio(2f/3f)
            )
            NotaEstrella(
                pelicula.vote_average,
                size = 45.dp,
                modifier = Modifier.align(Alignment.TopEnd).padding(4.dp)
            )
        }
        Text(
            text = pelicula.title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = pelicula.release_date.take(4),
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 12.dp),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}