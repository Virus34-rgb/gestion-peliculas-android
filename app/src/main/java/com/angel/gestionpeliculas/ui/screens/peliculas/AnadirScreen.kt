package com.angel.gestionpeliculas.ui.screens.peliculas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.angel.gestionpeliculas.ui.components.pelicula.PeliculaForm

@Composable
fun AnadirScreen(navController: NavHostController) {
    PeliculaForm(navController)
}
