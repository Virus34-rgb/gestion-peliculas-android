package com.angel.gestionpeliculas.ui.components.pelicula

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angel.gestionpeliculas.model.TMDBGenres
import com.angel.gestionpeliculas.ui.components.common.BaseScreen
import com.angel.gestionpeliculas.ui.components.common.BotonPrincipal
import com.angel.gestionpeliculas.ui.components.common.PantallaConMenu
import com.angel.gestionpeliculas.ui.components.search.BuscadorTMDB
import com.angel.gestionpeliculas.viewmodel.PeliculaViewModel
import kotlinx.coroutines.delay
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeliculaForm(
    navController: NavController,
    id: Long? = null,
) {
    val generosDisponibles = TMDBGenres.genres.values.toList()
    val viewModel: PeliculaViewModel = viewModel()
    val resena by viewModel.resena.observeAsState()
    val condicion by viewModel.condicion.observeAsState()
    val peliculasTMDB by viewModel.peliculasTMDB.observeAsState()
    var tmdbId: Long? by remember { mutableStateOf(null) }
    var nombre by remember { mutableStateOf("") }
    var fecha: LocalDate? by remember { mutableStateOf(resena?.fecha) }
    var notaSlider by remember { mutableFloatStateOf(resena?.nota?.toFloat() ?: 0f) }
    var opinion: String? by remember { mutableStateOf(null) }
    val generosSeleccionados =
        remember {
            mutableStateListOf<String>().also {
                it.addAll(resena?.generos ?: emptyList())
            }
        }
    var posterPath: String? by remember { mutableStateOf("") }
    val cargando by viewModel.cargando.observeAsState()
    val error by viewModel.error.observeAsState()
    var intentoEnviar by remember { mutableStateOf(false) }
    // navegacion
    LaunchedEffect(condicion) {
        if (condicion == true) {
            Log.d("NAV", "Volviendo atrás desde Añadir")
            navController.popBackStack()
            viewModel.resetCond()
        }
    }
    // cargar datos al editar
    LaunchedEffect(resena) {
        resena?.let {
            nombre = it.nombre
            notaSlider = it.nota.toFloat()
            generosSeleccionados.clear()
            generosSeleccionados.addAll(it.generos)
            fecha = it.fecha
            posterPath = it.posterPath
            opinion = it.opinion
            tmdbId = it.tmdbId
        }
    }
    // SincronizarFormulario
    LaunchedEffect(id) {
        if (id != null) viewModel.getById(id)
    }
    // BuscarTMDB
    LaunchedEffect(nombre) {
        delay(400)
        if (nombre.length > 2) {
            viewModel.searchTMDB(nombre)
        }
    }
    BaseScreen(cargando, error, viewModel::resetError) {
        val titulo =
            if (id == null) {
                "Añadir reseña"
            } else {
                "Actualizar reseña"
            }
        PantallaConMenu(navController, titulo, mostrarBottomBar = false) { padding ->
            LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = padding) {
                item {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        BuscadorTMDB(
                            nombre,
                            { nombre = it },
                            peliculasTMDB,
                            {
                                seleccionarPeliculaTMDB(
                                    pelicula = it,
                                    onNombre = { nombre = it },
                                    onFecha = { fecha = it },
                                    onPoster = { posterPath = it },
                                    onGeneros = {
                                        generosSeleccionados.clear()
                                        generosSeleccionados.addAll(it)
                                    },
                                    onTmdbId = { tmdbId = it },
                                )
                            },
                            cargando != true,
                            intentoEnviar && nombre.isBlank(),
                            "Ingrese el nombre",
                        )
                        SelectorGeneros(
                            generosSeleccionados,
                            generosDisponibles,
                            cargando != true,
                            intentoEnviar && generosSeleccionados.isEmpty(),
                            "Seleccione Generos",
                        )
                        SelectorFecha(
                            fecha = fecha,
                            onFechaSeleccionada = { nuevaFecha -> fecha = nuevaFecha },
                            enabled = cargando != true,
                            intentoEnviar && fecha == null,
                            "Seleccione la fecha de salida",
                        )
                        NotaOpinionEditor(
                            notaSlider,
                            { notaSlider = it },
                            opinion,
                            { opinion = it },
                            cargando != true,
                        )
                        BotonPrincipal(titulo, cargando != true, Modifier) {
                            intentoEnviar = true
                            if (!(nombre.isBlank() || generosSeleccionados.isEmpty() || fecha == null)) {
                                val notaDouble = notaSlider.toDouble()
                                val request =
                                    crearResenaRequest(
                                        tmdbId,
                                        nombre,
                                        generosSeleccionados,
                                        fecha!!,
                                        posterPath,
                                        notaDouble,
                                        opinion,
                                    )
                                Log.d("RESEÑA", "tmdbId enviado: ${request.tmdbId}")
                                if (id == null) {
                                    viewModel.add(request)
                                } else {
                                    viewModel.update(id, request)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
