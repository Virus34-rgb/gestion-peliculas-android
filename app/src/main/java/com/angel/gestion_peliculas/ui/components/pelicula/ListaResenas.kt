package com.angel.gestion_peliculas.ui.components.pelicula

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Movie
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.angel.gestion_peliculas.model.Resena
import com.angel.gestion_peliculas.ui.components.common.EmptyState
import my.nanihadesuka.compose.LazyColumnScrollbar
import my.nanihadesuka.compose.ScrollbarSettings

@Composable
fun ListaResenas(
    lista: LazyListState,
    resenas: List<Resena>,
    cargando: Boolean?,
    navController: NavController,
    modifier: Modifier = Modifier
) {
    LazyColumnScrollbar(
        state = lista,
        settings = ScrollbarSettings.Default,
        modifier = modifier
    ) {
        if (resenas.isEmpty() && cargando == false) {
            EmptyState(Icons.Default.Movie, "Aún no has añadido ninguna reseña")
        } else {
            LazyColumn(state = lista, verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(resenas, key = { resena -> resena.resenaId ?: resena.nombre }) { resena ->
                    PeliculaCard(resena, cargando ){
                        navController.navigate("detalle/${resena.resenaId}")
                    }
                }
            }
        }
    }
}