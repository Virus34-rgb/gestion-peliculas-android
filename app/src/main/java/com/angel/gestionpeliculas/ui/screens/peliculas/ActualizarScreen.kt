package com.angel.gestionpeliculas.ui.screens.peliculas

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.angel.gestionpeliculas.ui.components.pelicula.PeliculaForm

@Composable
fun ActualizarScreen(
    navController: NavController,
    id: Long,
) {
    PeliculaForm(navController, id)
}
