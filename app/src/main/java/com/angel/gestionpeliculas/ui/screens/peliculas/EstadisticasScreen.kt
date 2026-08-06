package com.angel.gestionpeliculas.ui.screens.peliculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angel.gestionpeliculas.model.parametros.ActividadCardData
import com.angel.gestionpeliculas.model.parametros.EmptyStateData
import com.angel.gestionpeliculas.ui.components.common.BaseScreen
import com.angel.gestionpeliculas.ui.components.common.EmptyState
import com.angel.gestionpeliculas.ui.components.common.PantallaConMenu
import com.angel.gestionpeliculas.ui.components.statistics.ActividadCard
import com.angel.gestionpeliculas.ui.components.statistics.DistribucionNotasCard
import com.angel.gestionpeliculas.ui.components.statistics.EvolutionCard
import com.angel.gestionpeliculas.ui.components.statistics.GenerosFavoritosCard
import com.angel.gestionpeliculas.ui.components.statistics.RachaCard
import com.angel.gestionpeliculas.ui.components.statistics.ResumenGeneralCard
import com.angel.gestionpeliculas.ui.components.statistics.StatSection
import com.angel.gestionpeliculas.ui.components.statistics.UltimaResenaCard
import com.angel.gestionpeliculas.viewmodel.PeliculaViewModel
import java.time.YearMonth

@Composable
fun EstadisticasScreen(navController: NavController) {
    val viewModel: PeliculaViewModel = viewModel()
    val resumen by viewModel.resumen.observeAsState()
    val notas by viewModel.notas.observeAsState()
    val generos by viewModel.generos.observeAsState()
    val actividad by viewModel.actividad.observeAsState()
    val resena by viewModel.ultimaResena.observeAsState()
    val limiteInf by viewModel.limiteInf.observeAsState()
    val limiteSup by viewModel.limiteSup.observeAsState()
    val racha by viewModel.racha.observeAsState()
    val evolucion by viewModel.evolucion.observeAsState()
    var mesActual: YearMonth by remember { mutableStateOf(YearMonth.now()) }
    val cargando by viewModel.cargando.observeAsState()
    val error by viewModel.error.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.cargarEstadisticas(12)
    }
    BaseScreen(cargando, error, viewModel::resetError) {
        PantallaConMenu(navController, "Estadisticas", true) { padding ->
            if (resumen?.numero == 0) {
                MostrarEstadoVacio(cargando == true, { navController.navigate("buscar") }, padding)
            } else {
                LazyColumn(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(padding),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    if (cargando == false) {
                        item {
                            StatSection("Resumen") { ResumenGeneralCard(resumen) }
                        }
                        item {
                            StatSection("Racha") { RachaCard(racha) }
                        }
                        item {
                            StatSection("Distribucion de notas") {
                                DistribucionNotasCard(notas)
                            }
                        }
                        item {
                            StatSection("Distribución de generos") {
                                GenerosFavoritosCard(generos)
                            }
                        }
                        item {
                            StatSection("Actividad") {
                                ActividadCard(
                                    ActividadCardData(
                                        mesActual,
                                        actividad,
                                        limiteSup,
                                        limiteInf,
                                        { mesActual = mesActual.minusMonths(1) },
                                        { mesActual = mesActual.plusMonths(1) },
                                    ),
                                )
                            }
                        }
                        item {
                            StatSection("Última Reseña") {
                                UltimaResenaCard(resena, cargando)
                            }
                        }
                        item {
                            StatSection("Evolucion") {
                                EvolutionCard(evolucion)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MostrarEstadoVacio(
    cargando: Boolean,
    onClick: () -> Unit,
    padding: PaddingValues,
) {
    if (cargando == false) {
        EmptyState(
            EmptyStateData(
                Icons.Default.BarChart,
                "Aún no hay estadísticas",
                "Empieza creando tu primera reseña para ver tu actividad, géneros favoritos y evolución de notas.",
                "Buscar",
                onClick,
                Modifier.fillMaxSize().padding(padding),
            ),
        )
    }
}
