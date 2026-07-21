package com.angel.gestion_peliculas.ui.components.statistics

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResumenGeneralCard(
    numResenas: Int,
    notaMedia: Double,
    mejorPelicula: String,
    peorPelicula: String
) {
    Card(shape = RoundedCornerShape(16.dp),modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)) {
        Column(verticalArrangement = spacedBy(8.dp), modifier = Modifier.padding(8.dp)){
            ResumenItem("Numero de Reseñas",numResenas.toString())
            ResumenItem("Nota media",notaMedia.toString())
            ResumenItem("Mejor Pelicula", mejorPelicula)
            ResumenItem("Peor Pelicula", peorPelicula)
        }
    }
}
@Composable
private fun ResumenItem(titulo : String, contenido : String){
    Row(Modifier.fillMaxWidth()){
        Text(text = titulo, style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.weight(1f))
        Text(text = contenido, style = MaterialTheme.typography.bodyMedium)
    }
}