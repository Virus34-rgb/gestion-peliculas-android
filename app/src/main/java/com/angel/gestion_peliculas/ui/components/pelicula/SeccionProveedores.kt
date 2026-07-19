package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.angel.gestion_peliculas.model.CountryProviders
import com.angel.gestion_peliculas.model.Provider

@Composable
fun SeccionProveedores(providers: CountryProviders?) {
    if (providers == null) return
    ProveedoresCard("Disponible en:", providers!!.flatrate)
    ProveedoresCard("Alquilable en:", providers!!.rent)
    ProveedoresCard("Se puede comprar en:", providers!!.buy)
}

@Composable
private fun ProveedoresCard(text: String, providers: List<Provider>?) {
    if (!providers.isNullOrEmpty()) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
        LazyRow() {
            items(providers) { provider ->
                ImagenPath(
                    provider.logoPath,
                    provider.providerName,
                    Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}