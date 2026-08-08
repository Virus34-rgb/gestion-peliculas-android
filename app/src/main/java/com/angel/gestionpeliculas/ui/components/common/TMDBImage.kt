package com.angel.gestionpeliculas.ui.components.common

fun aTMDBImage(
    path: String,
    cod: String,
): String = "https://image.tmdb.org/t/p/${cod}$path"
