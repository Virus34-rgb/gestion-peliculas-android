package com.angel.gestionpeliculas.ui.screens.resenas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RateReview
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angel.gestionpeliculas.model.parametros.EmptyStateData
import com.angel.gestionpeliculas.ui.components.common.BaseScreen
import com.angel.gestionpeliculas.ui.components.common.EmptyState
import com.angel.gestionpeliculas.ui.components.common.PantallaConMenu
import com.angel.gestionpeliculas.ui.components.pelicula.ResenaPublicaCard
import com.angel.gestionpeliculas.viewmodel.BuscarViewModel

@Composable
fun ResenaPeliculaScreen(
    navController: NavController,
    peliculaId: Long,
) {
    val viewModel: BuscarViewModel = viewModel()
    val resenas by viewModel.resenas.observeAsState(emptyList())
    val cargando by viewModel.cargando.observeAsState()
    val error by viewModel.error.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.cargarResenasDePelicula(peliculaId)
    }
    BaseScreen(cargando, error, viewModel::resetError) {
        PantallaConMenu(navController, "Reseñas", mostrarBottomBar = false) { padding ->
            if (resenas.isEmpty() && cargando == false) {
                EmptyState(
                    EmptyStateData(Icons.Default.RateReview, "Todavía nadie ha publicado una reseña"),
                )
            } else if (cargando == false) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize().padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    items(resenas) { resena ->
                        ResenaPublicaCard(resena, false)
                    }
                }
            }
        }
    }
}
