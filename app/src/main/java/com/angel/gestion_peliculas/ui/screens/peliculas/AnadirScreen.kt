package com.angel.gestion_peliculas.ui.screens.peliculas

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.angel.gestion_peliculas.ui.components.pelicula.PeliculaForm

@Composable
fun AnadirScreen(navController: NavHostController) {
    PeliculaForm(navController)
}