package com.angel.gestionpeliculas.ui.screens.peliculas

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angel.gestionpeliculas.ui.components.common.BaseScreen
import com.angel.gestionpeliculas.ui.components.common.BotonPrincipal
import com.angel.gestionpeliculas.ui.components.common.PantallaConMenu
import com.angel.gestionpeliculas.ui.components.filters.SortBar
import com.angel.gestionpeliculas.ui.components.pelicula.ListaResenas
import com.angel.gestionpeliculas.viewmodel.PeliculaViewModel
import kotlin.collections.emptyList

@Composable
fun ListaScreen(navController: NavController) {
    val viewModel: PeliculaViewModel = viewModel()
    val resenas by viewModel.resenas.observeAsState(emptyList())
    val cargando by viewModel.cargando.observeAsState()
    val lista: LazyListState = rememberLazyListState()
    val error by viewModel.error.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.getAll()
    }
    LaunchedEffect(resenas) {
        lista.scrollToItem(0)
    }
    BaseScreen(cargando, error, viewModel::resetError) {
        PantallaConMenu(navController, "Películas", mostrarBottomBar = true) { padding ->
            Column(
                modifier = Modifier.fillMaxSize().padding(padding),
                verticalArrangement = spacedBy(8.dp),
            ) {
                SortBar(
                    cargando = cargando,
                    onOrdenar = { criterio, ascendente -> viewModel.getAllFiltered(criterio, ascendente) },
                )
                ListaResenas(lista, resenas, cargando, navController, Modifier.weight(1f))
                BotonPrincipal("Añadir película", cargando != true, Modifier.fillMaxWidth().padding(16.dp)) {
                    navController.navigate("anadir")
                }
            }
        }
    }
}
