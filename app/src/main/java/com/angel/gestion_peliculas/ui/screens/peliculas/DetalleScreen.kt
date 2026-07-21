package com.angel.gestion_peliculas.ui.screens.peliculas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angel.gestion_peliculas.model.Resena
import com.angel.gestion_peliculas.model.ResenaPublicaResponse
import com.angel.gestion_peliculas.model.TMDBMovie
import com.angel.gestion_peliculas.network.TokenManager
import com.angel.gestion_peliculas.ui.components.common.BaseScreen
import com.angel.gestion_peliculas.ui.components.common.EmptyState
import com.angel.gestion_peliculas.ui.components.common.PantallaConMenu
import com.angel.gestion_peliculas.ui.components.pelicula.DetalleCatalogoContent
import com.angel.gestion_peliculas.ui.components.pelicula.DetalleResenaContent
import com.angel.gestion_peliculas.ui.components.pelicula.crearResenaRequest
import com.angel.gestion_peliculas.viewmodel.BuscarViewModel
import com.angel.gestion_peliculas.viewmodel.PeliculaViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(navController: NavController, resenaId: Long?, tmdbId: Long?) {
    val viewModel: PeliculaViewModel = viewModel()
    val buscarViewModel: BuscarViewModel = viewModel()
    val resena: Resena? by viewModel.resena.observeAsState()
    val cargando = if (resenaId != null) viewModel.cargando.observeAsState().value
    else buscarViewModel.cargando.observeAsState().value
    var mostrarDialogoBorrado by remember { mutableStateOf(false) }
    val error by viewModel.error.observeAsState()
    val detalleTMDB by buscarViewModel.detalleTMDB.observeAsState()
    val peliculaResponse by buscarViewModel.peliculaResponse.observeAsState()
    val resenasPublicas by buscarViewModel.resenas.observeAsState(emptyList())
    val watchProviders by buscarViewModel.watchProviders.observeAsState()
    var notaSlider by remember { mutableFloatStateOf(resena?.nota?.toFloat() ?: 0f) }
    var opinion: String? by remember { mutableStateOf(null) }
    var miResenaExistente by remember { mutableStateOf<ResenaPublicaResponse?>(null) }
    LaunchedEffect(Unit) {
        if (resenaId != null) viewModel.getById(resenaId)
        else if (tmdbId != null) buscarViewModel.buscarPorTMDB(tmdbId)
    }
    LaunchedEffect(resenasPublicas) {
        miResenaExistente = resenasPublicas.find { it.username == TokenManager.getUsername() }
        if (miResenaExistente != null) {
            notaSlider = miResenaExistente!!.nota.toFloat()
            opinion = miResenaExistente!!.opinion
        }
    }
    if (resenaId != null && resena == null && cargando == false) {
        EmptyState(Icons.Default.SearchOff, "Reseña no encontrada")
    } else if (tmdbId != null && detalleTMDB == null && cargando == false) {
        EmptyState(Icons.Default.SearchOff, "Pelicula no encontrada")
    }
    BaseScreen(cargando, error, viewModel::resetError) {
        PantallaConMenu(navController, "Detalles", mostrarBottomBar = false) { padding ->
            if (resenaId != null) {
                resena?.let {
                    DetalleResenaContent(
                        resena, cargando == true, mostrarDialogoBorrado,
                        {
                            viewModel.delete(resenaId!!)
                            mostrarDialogoBorrado = false
                            navController.popBackStack()
                        },
                        { mostrarDialogoBorrado = false }, { mostrarDialogoBorrado = true },
                        { navController.navigate("actualizar/$resenaId") },
                        Modifier
                            .fillMaxSize()
                            .padding(padding)
                    )
                }
            } else if (detalleTMDB != null) {
                DetalleCatalogoContent(
                    detalleTMDB, watchProviders, resenasPublicas,
                    notaSlider, opinion, cargando, { notaSlider = it },
                    { opinion = it }, Modifier
                        .fillMaxSize()
                        .padding(padding),
                    { navController.navigate("resenasPelicula/${peliculaResponse!!.peliculaId}") })
                {
                    guardarResenaRapido(
                        buscarViewModel, miResenaExistente, tmdbId!!, detalleTMDB!!,
                        notaSlider, opinion
                    )
                }
            }
        }
    }
}

private fun guardarResenaRapido(
    buscarViewModel: BuscarViewModel, miResenaExistente: ResenaPublicaResponse?,
    tmdbId: Long, detalleTMDB: TMDBMovie, nota: Float, opinion: String?
) {
    val notaDouble = nota.toDouble()
    val resena = crearResenaRequest(
        tmdbId, detalleTMDB.title,
        detalleTMDB.genres?.map { it.name } ?: emptyList(),
        LocalDate.parse(detalleTMDB.release_date),
        detalleTMDB.poster_path, notaDouble, opinion)
    if (miResenaExistente == null) {
        buscarViewModel.addResenaRapido(resena)
    } else {
        buscarViewModel.updateResenaRapido(miResenaExistente!!.resenaId, resena)
    }
}
