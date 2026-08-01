package com.angel.gestionpeliculas.model

import java.time.LocalDate

data class ActivityDayData(
    val dia: LocalDate?,
    val intensidad: Int,
    val pertenece: Boolean,
)
