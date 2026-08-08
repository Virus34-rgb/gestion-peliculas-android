package com.angel.gestionpeliculas.ui.screens.peliculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.angel.gestionpeliculas.model.parametros.EmptyStateData
import com.angel.gestionpeliculas.ui.components.common.BaseScreen
import com.angel.gestionpeliculas.ui.components.common.EmptyState
import com.angel.gestionpeliculas.ui.components.common.PantallaConMenu
import com.angel.gestionpeliculas.ui.components.pelicula.PeliculaBusquedaCard
import com.angel.gestionpeliculas.ui.components.search.BuscadorPeliculas
import com.angel.gestionpeliculas.viewmodel.BuscarViewModel
import kotlinx.coroutines.delay

@Composable
fun BuscarScreen(navController: NavHostController) {
    val viewModel: BuscarViewModel = viewModel()
    var query by remember { mutableStateOf("") }
    val peliculasTMDB by viewModel.peliculasTMDB.collectAsState()
    val error by viewModel.error.collectAsState()
    val cargando by viewModel.cargando.collectAsState()
    LaunchedEffect(query) {
        delay(400)
        val texto = query.trim()

        if (texto.length > 2) {
            viewModel.searchTMDB(texto)
        } else {
            viewModel.limpiarBusqueda()
        }
    }
    BaseScreen(cargando, error, viewModel::resetError) {
        PantallaConMenu(navController, "Buscar", mostrarBottomBar = true) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding),
            ) {
                BuscadorPeliculas(query, Modifier.padding(16.dp)) {
                    query = it
                }
                when {
                    query.isBlank() ->
                        EmptyState(
                            EmptyStateData(Icons.Default.Search, "Busca una película para comenzar"),
                        )

                    peliculasTMDB.isEmpty() && cargando == false ->
                        EmptyState(
                            EmptyStateData(Icons.Default.SearchOff, "No se encontraron películas"),
                        )

                    else ->
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(160.dp),
                            contentPadding = PaddingValues(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(12.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            items(peliculasTMDB) { pelicula ->
                                PeliculaBusquedaCard(
                                    pelicula = pelicula,
                                    onclick = { navController.navigate("detalleCatalogo/${pelicula.id}") },
                                    cargando = cargando,
                                )
                            }
                        }
                }
            }
        }
    }
}
