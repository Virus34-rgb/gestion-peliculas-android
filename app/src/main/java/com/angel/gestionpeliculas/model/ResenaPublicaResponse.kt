package com.angel.gestionpeliculas.model

import java.time.LocalDate

data class ResenaPublicaResponse(
    val resenaId: Long,
    val username: String,
    val nota: Double,
    val opinion: String?,
    val fechaPublicacion: LocalDate?,
)
