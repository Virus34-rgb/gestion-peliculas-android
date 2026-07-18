package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angel.gestion_peliculas.model.CountryProviders
import com.angel.gestion_peliculas.model.ResenaPublicaResponse
import com.angel.gestion_peliculas.model.TMDBMovie

@Composable
fun DetalleCatalogoContent(detalleTMDB : TMDBMovie?, watchProviders : CountryProviders?,
                           resenasPublicas : List<ResenaPublicaResponse>,
                           notaSlider : Float, opinion : String?, cargando : Boolean?,
                           onNotaChange : (Float) -> Unit, onOpinionChange : (String) -> Unit,
                           modifier : Modifier,onResenasPublicas : () -> Unit,onGuardar : () -> Unit){
    LazyColumn(modifier = modifier) {
        item{
            CabeceraPelicula(detalleTMDB!!.poster_path,detalleTMDB!!.title,
                detalleTMDB!!.vote_average, modifier = Modifier)
            InformacionPelicula(generos = detalleTMDB!!.genres?.map { it.name } ?: emptyList(),
                detalleTMDB!!.release_date) {
                Text(
                    text = "Cantidad de votos:${detalleTMDB!!.vote_count.toString()}",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold
                ) // Hacerlo mas pequeño
                Text(
                    text = "Sinopsis: ${detalleTMDB!!.overview}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            Card(Modifier.fillMaxWidth().padding(8.dp)) {
                SeccionProveedores(watchProviders)
            }
            SeccionResenasPublicas(resenasPublicas,onResenasPublicas)
            FormularioResenaRapida(notaSlider, onNotaChange = onNotaChange,
                opinion, onOpinionChange = onOpinionChange ,cargando != true) {
                onGuardar()
            }
        }
    }
}