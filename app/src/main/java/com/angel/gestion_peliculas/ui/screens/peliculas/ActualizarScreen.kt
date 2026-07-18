package com.angel.gestion_peliculas.ui.screens.peliculas

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.angel.gestion_peliculas.ui.components.pelicula.PeliculaForm

@Composable
fun ActualizarScreen(navController: NavController, id: Long) {
    PeliculaForm(navController, id)
}