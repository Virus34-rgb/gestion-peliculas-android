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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angel.gestionpeliculas.model.TMDBGenres
import com.angel.gestionpeliculas.model.parametros.BuscadorTMDBData
import com.angel.gestionpeliculas.model.parametros.CrearResenaRequestData
import com.angel.gestionpeliculas.model.parametros.GuardarResenaData
import com.angel.gestionpeliculas.model.parametros.HandleResenaSyncEffectData
import com.angel.gestionpeliculas.model.parametros.PeliculaFormContentData
import com.angel.gestionpeliculas.model.parametros.SeleccionarPeliculaTMDBData
import com.angel.gestionpeliculas.ui.components.common.BaseScreen
import com.angel.gestionpeliculas.ui.components.common.BotonPrincipal
import com.angel.gestionpeliculas.ui.components.common.PantallaConMenu
import com.angel.gestionpeliculas.ui.components.search.BuscadorTMDB
import com.angel.gestionpeliculas.viewmodel.PeliculaViewModel
import kotlinx.coroutines.delay
import java.time.LocalDate
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeliculaForm(
    navController: NavController,
    id: Long? = null,
) {
    val generosDisponibles = TMDBGenres.genres.values.toList()
    val viewModel: PeliculaViewModel = viewModel()
    val resena by viewModel.resena.collectAsState()
    val condicion by viewModel.condicion.collectAsState()
    val peliculasTMDB by viewModel.peliculasTMDB.collectAsState()
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
    val cargando by viewModel.cargando.collectAsState()
    val error by viewModel.error.collectAsState()
    var intentoEnviar by remember { mutableStateOf(false) }
    HandleNavigationEffect(condicion, navController, viewModel)
    HandleResenaSyncEffect(
        HandleResenaSyncEffectData(
            resena = resena,
            onNombre = { nombre = it },
            onNota = { notaSlider = it },
            onGeneros = {
                generosSeleccionados.clear()
                generosSeleccionados.addAll(it)
            },
            onFecha = { fecha = it },
            onPoster = { posterPath = it },
            onOpinion = { opinion = it },
            onTmdbId = { tmdbId = it },
        ),
    )
    HandleEditLoadEffect(id, viewModel)
    HandleTmdbSearchEffect(nombre, viewModel)
    BaseScreen(cargando, error, viewModel::resetError) {
        val titulo =
            if (id == null) {
                "Añadir reseña"
            } else {
                "Actualizar reseña"
            }
        PantallaConMenu(navController, titulo, mostrarBottomBar = false) { padding ->
            PeliculaFormContent(
                PeliculaFormContentData(
                    navController = navController,
                    titulo = titulo,
                    nombre = nombre,
                    onNombreChange = { nombre = it },
                    peliculasTMDB = peliculasTMDB,
                    onSeleccionarTMDB = { /* si decides mover esta lógica fuera */ },
                    generosSeleccionados = generosSeleccionados,
                    generosDisponibles = generosDisponibles,
                    fecha = fecha,
                    onFechaSeleccionada = { fecha = it },
                    notaSlider = notaSlider,
                    onNotaChange = { notaSlider = it },
                    opinion = opinion,
                    onOpinionChange = { opinion = it },
                    posterPath = posterPath,
                    onPosterChange = { posterPath = it },
                    tmdbId = tmdbId,
                    onTmdbIdChange = { tmdbId = it },
                    cargando = cargando,
                    intentoEnviar = intentoEnviar,
                    onGuardar = {
                        guardarResena(
                            GuardarResenaData(
                                id = id,
                                viewModel = viewModel,
                                onIntentoEnviar = { intentoEnviar = it },
                                nombre = nombre,
                                generosSeleccionados = generosSeleccionados,
                                fecha = fecha,
                                tmdbId = tmdbId,
                                posterPath = posterPath,
                                notaSlider = notaSlider,
                                opinion = opinion,
                            ),
                        )
                    },
                    padding = padding,
                ),
            )
        }
    }
}

@Composable
private fun HandleNavigationEffect(
    condicion: Boolean?,
    navController: NavController,
    viewModel: PeliculaViewModel,
) {
    LaunchedEffect(condicion) {
        if (condicion == true) {
            navController.popBackStack()
            viewModel.resetCond()
        }
    }
}

@Composable
private fun HandleResenaSyncEffect(param: HandleResenaSyncEffectData) {
    LaunchedEffect(param.resena) {
        param.resena?.let {
            param.onNombre(it.nombre)
            param.onNota(it.nota.toFloat())
            param.onGeneros(it.generos)
            param.onFecha(it.fecha)
            param.onPoster(it.posterPath)
            param.onOpinion(it.opinion)
            param.onTmdbId(it.tmdbId)
        }
    }
}

@Composable
private fun HandleEditLoadEffect(
    id: Long?,
    viewModel: PeliculaViewModel,
) {
    LaunchedEffect(id) {
        if (id != null) {
            viewModel.getById(id)
        }
    }
}

@Composable
private fun HandleTmdbSearchEffect(
    nombre: String,
    viewModel: PeliculaViewModel,
) {
    LaunchedEffect(nombre) {
        delay(400.milliseconds)
        if (nombre.length > 2) {
            viewModel.searchTMDB(nombre)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PeliculaFormContent(param: PeliculaFormContentData) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = param.padding,
    ) {
        item {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                BuscadorTMDB(
                    BuscadorTMDBData(
                        param.nombre,
                        param.onNombreChange,
                        param.peliculasTMDB,
                        {
                            seleccionarPeliculaTMDB(
                                SeleccionarPeliculaTMDBData(
                                    pelicula = it,
                                    onNombre = param.onNombreChange,
                                    onFecha = param.onFechaSeleccionada,
                                    onPoster = param.onPosterChange,
                                    onGeneros = {
                                        param.generosSeleccionados.clear()
                                        param.generosSeleccionados.addAll(it)
                                    },
                                    onTmdbId = param.onTmdbIdChange,
                                ),
                            )
                        },
                        param.cargando != true,
                        param.intentoEnviar && param.nombre.isBlank(),
                        "Ingrese el nombre",
                    ),
                )
                SelectorGeneros(
                    param.generosSeleccionados as SnapshotStateList<String>,
                    param.generosDisponibles,
                    param.cargando != true,
                    param.intentoEnviar && param.generosSeleccionados.isEmpty(),
                    "Seleccione Generos",
                )
                SelectorFecha(
                    fecha = param.fecha,
                    onFechaSeleccionada = param.onFechaSeleccionada,
                    enabled = param.cargando != true,
                    isError = param.intentoEnviar && param.fecha == null,
                    mensajeError = "Seleccione la fecha de salida",
                )
                NotaOpinionEditor(
                    param.notaSlider,
                    param.onNotaChange,
                    param.opinion,
                    param.onOpinionChange,
                    param.cargando != true,
                )
                BotonPrincipal(
                    param.titulo,
                    param.cargando != true,
                    Modifier,
                    param.onGuardar,
                )
            }
        }
    }
}

private fun guardarResena(param: GuardarResenaData) {
    param.onIntentoEnviar(true)
    if (param.nombre.isBlank() || param.generosSeleccionados.isEmpty() || param.fecha == null) {
        return
    }
    val request =
        crearResenaRequest(
            CrearResenaRequestData(
                tmdbId = param.tmdbId,
                nombre = param.nombre,
                generos = param.generosSeleccionados,
                fecha = param.fecha,
                posterPath = param.posterPath,
                nota = param.notaSlider.toDouble(),
                opinion = param.opinion,
            ),
        )
    Log.d("RESEÑA", "tmdbId enviado: ${request.tmdbId}")
    if (param.id == null) {
        param.viewModel.add(request)
    } else {
        param.viewModel.update(param.id, request)
    }
}
