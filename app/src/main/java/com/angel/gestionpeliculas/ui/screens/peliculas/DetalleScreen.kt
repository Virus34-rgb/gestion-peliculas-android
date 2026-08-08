package com.angel.gestionpeliculas.ui.screens.peliculas

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SearchOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.model.ResenaPublicaResponse
import com.angel.gestionpeliculas.model.parametros.CrearResenaRequestData
import com.angel.gestionpeliculas.model.parametros.DetalleCatalogoContentData
import com.angel.gestionpeliculas.model.parametros.DetalleResenaContentData
import com.angel.gestionpeliculas.model.parametros.EmptyStateData
import com.angel.gestionpeliculas.model.parametros.GuardarResenaRapidoData
import com.angel.gestionpeliculas.network.TokenManager
import com.angel.gestionpeliculas.ui.components.common.BaseScreen
import com.angel.gestionpeliculas.ui.components.common.EmptyState
import com.angel.gestionpeliculas.ui.components.common.PantallaConMenu
import com.angel.gestionpeliculas.ui.components.pelicula.DetalleCatalogoContent
import com.angel.gestionpeliculas.ui.components.pelicula.DetalleResenaContent
import com.angel.gestionpeliculas.ui.components.pelicula.crearResenaRequest
import com.angel.gestionpeliculas.viewmodel.BuscarViewModel
import com.angel.gestionpeliculas.viewmodel.PeliculaViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleScreen(
    navController: NavController,
    resenaId: Long?,
    tmdbId: Long?,
) {
    val viewModel: PeliculaViewModel = viewModel()
    val buscarViewModel: BuscarViewModel = viewModel()
    val resena: Resena? by viewModel.resena.collectAsState()

    val cargando =
        if (resenaId != null) {
            viewModel.cargando.collectAsState().value
        } else {
            buscarViewModel.cargando.collectAsState().value
        }
    var mostrarDialogoBorrado by remember { mutableStateOf(false) }
    val error by viewModel.error.collectAsState()

    val detalleTMDB by buscarViewModel.detalleTMDB.collectAsState()
    val peliculaResponse by buscarViewModel.peliculaResponse.collectAsState()
    val resenasPublicas by buscarViewModel.resenas.collectAsState()
    val watchProviders by buscarViewModel.watchProviders.collectAsState()
    val credits by buscarViewModel.credits.collectAsState()
    val similar by buscarViewModel.similar.collectAsState()
    val videos by buscarViewModel.videos.collectAsState()
    val collection by buscarViewModel.collection.collectAsState()

    var notaSlider by remember { mutableFloatStateOf(resena?.nota?.toFloat() ?: 0f) }
    var opinion: String? by remember { mutableStateOf(null) }
    var miResenaExistente by remember { mutableStateOf<ResenaPublicaResponse?>(null) }
    HandleCargaDetalleEffect(
        resenaId = resenaId,
        tmdbId = tmdbId,
        viewModel = viewModel,
        buscarViewModel = buscarViewModel,
    )
    HandleMiResenaEffect(
        resenasPublicas = resenasPublicas,
        onMiResenaExistente = { miResenaExistente = it },
        onNota = { notaSlider = it },
        onOpinion = { opinion = it },
    )
    if (resenaId != null && resena == null && cargando == false) {
        EmptyState(
            EmptyStateData(Icons.Default.SearchOff, "Reseña no encontrada"),
        )
    } else if (tmdbId != null && detalleTMDB == null && cargando == false) {
        EmptyState(
            EmptyStateData(Icons.Default.SearchOff, "Pelicula no encontrada"),
        )
    }
    BaseScreen(cargando, error, viewModel::resetError) {
        PantallaConMenu(navController, "Detalles", mostrarBottomBar = false) { padding ->
            if (resenaId != null) {
                resena?.let {
                    DetalleResenaContent(
                        DetalleResenaContentData(
                            resena,
                            cargando == true,
                            mostrarDialogoBorrado,
                            {
                                viewModel.delete(resenaId)
                                mostrarDialogoBorrado = false
                                navController.popBackStack()
                            },
                            { mostrarDialogoBorrado = false },
                            { mostrarDialogoBorrado = true },
                            { navController.navigate("actualizar/$resenaId") },
                            { navController.popBackStack() },
                            Modifier
                                .fillMaxSize()
                                .padding(padding),
                        ),
                    )
                }
            } else if (detalleTMDB != null) {
                DetalleCatalogoContent(
                    DetalleCatalogoContentData(
                        detalleTMDB,
                        watchProviders,
                        resenasPublicas,
                        videos,
                        credits,
                        collection,
                        similar,
                        notaSlider,
                        opinion,
                        cargando,
                        { notaSlider = it },
                        { opinion = it },
                        Modifier
                            .fillMaxSize()
                            .padding(padding),
                        { navController.navigate("resenasPelicula/${peliculaResponse!!.peliculaId}") },
                        { navController.popBackStack() },
                        {
                            guardarResenaRapido(
                                GuardarResenaRapidoData(
                                    buscarViewModel,
                                    miResenaExistente,
                                    tmdbId!!,
                                    detalleTMDB!!,
                                    notaSlider,
                                    opinion,
                                ),
                            )
                        },
                        navController,
                    ),
                )
            }
        }
    }
}

private fun guardarResenaRapido(param: GuardarResenaRapidoData) {
    val notaDouble = param.nota.toDouble()
    val resena =
        crearResenaRequest(
            CrearResenaRequestData(
                param.tmdbId,
                param.detalleTMDB.title,
                param.detalleTMDB.genres?.map { it.name } ?: emptyList(),
                LocalDate.parse(param.detalleTMDB.releaseDate),
                param.detalleTMDB.posterPath,
                notaDouble,
                param.opinion,
            ),
        )
    if (param.miResenaExistente == null) {
        param.buscarViewModel.addResenaRapido(resena)
    } else {
        param.buscarViewModel.updateResenaRapido(param.miResenaExistente.resenaId, resena)
    }
}

@Composable
private fun HandleCargaDetalleEffect(
    resenaId: Long?,
    tmdbId: Long?,
    viewModel: PeliculaViewModel,
    buscarViewModel: BuscarViewModel,
) {
    LaunchedEffect(Unit) {
        if (resenaId != null) {
            viewModel.getById(resenaId)
        } else if (tmdbId != null) {
            buscarViewModel.buscarPorTMDB(tmdbId)
            buscarViewModel.getAllExtras(tmdbId)
        }
    }
}

@Composable
private fun HandleMiResenaEffect(
    resenasPublicas: List<ResenaPublicaResponse>,
    onMiResenaExistente: (ResenaPublicaResponse?) -> Unit,
    onNota: (Float) -> Unit,
    onOpinion: (String?) -> Unit,
) {
    LaunchedEffect(resenasPublicas) {
        val miResena =
            resenasPublicas.find {
                it.username == TokenManager.getUsername()
            }

        onMiResenaExistente(miResena)

        miResena?.let {
            onNota(it.nota.toFloat())
            onOpinion(it.opinion)
        }
    }
}
