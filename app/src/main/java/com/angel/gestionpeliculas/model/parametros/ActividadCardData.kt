package com.angel.gestionpeliculas.model.parametros

import com.angel.gestionpeliculas.model.ActividadData
import java.time.YearMonth

data class ActividadCardData(
    val mes: YearMonth,
    val lista: List<ActividadData>?,
    val limiteSup: YearMonth?,
    val limiteInf: YearMonth?,
    val onClickIzq: () -> Unit,
    val onClickDer: () -> Unit,
)
