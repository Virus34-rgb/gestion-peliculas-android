package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.angel.gestion_peliculas.model.Resena

@Composable
fun PeliculaCard (resena : Resena, cargando : Boolean?, navController: NavController){
    ElevatedCard (
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).clickable(enabled = cargando != true)
        {navController.navigate("detalle/${resena.resenaId}")},
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(16.dp)//debería ser mas?
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            ImagenPath(resena.posterPath, resena.nombre, Modifier.width(60.dp).height(90.dp))
            Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(8.dp)){
                Text(
                    text = resena.nombre,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = resena.generos.joinToString(", "),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = resena.fecha.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }
            NotaEstrella(resena.nota, 60.dp, null)
        }
    }
}
