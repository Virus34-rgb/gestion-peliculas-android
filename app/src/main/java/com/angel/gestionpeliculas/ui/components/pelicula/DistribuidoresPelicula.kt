package com.angel.gestionpeliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.angel.gestionpeliculas.model.CountryProviders
import com.angel.gestionpeliculas.model.Provider

@Composable
fun DistribuidoresPelicula(providers: CountryProviders?) {
    if (providers == null) return

    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "DONDE VER",
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
        )
        ListaProveedores("Disponible en:", providers!!.flatrate)
        ListaProveedores("Alquilable en:", providers!!.rent)
        ListaProveedores("Se puede comprar en:", providers!!.buy)
    }
}

@Composable
private fun ListaProveedores(
    text: String,
    providers: List<Provider>?,
) {
    if (!providers.isNullOrEmpty()) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
        )
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            items(providers) { provider ->
                ImagenPath(
                    provider.logoPath,
                    provider.providerName,
                    Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(8.dp)),
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
    }
}
