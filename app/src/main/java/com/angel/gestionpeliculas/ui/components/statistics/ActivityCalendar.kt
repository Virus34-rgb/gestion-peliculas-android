package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.angel.gestionpeliculas.model.ActividadData
import com.angel.gestionpeliculas.model.ActivityDayData
import java.time.YearMonth

@Composable
fun ActivityCalendar(
    mes: YearMonth,
    lista: List<ActividadData>?,
) {
    val dias = mes.lengthOfMonth()
    var indicePrimerDia = mes.atDay(1).dayOfWeek.value - 1
    val calendario = mutableListOf<ActivityDayData>()
    while (indicePrimerDia > 0) {
        calendario.add(ActivityDayData(null, 0, false))
        indicePrimerDia--
    }
    var dia = 1
    while (dia <= dias) {
        val actividad = lista!!.firstOrNull { it.fecha == mes.atDay(dia) }
        if (actividad != null) {
            calendario.add(ActivityDayData(mes.atDay(dia), actividad.cantidadResenas, true))
        } else {
            calendario.add(ActivityDayData(mes.atDay(dia), 0, true))
        }
        dia++
    }
    while (calendario.size % 7 != 0) {
        calendario.add(ActivityDayData(null, 0, false))
    }
    var diaCal: Int = 0
    while (calendario.size != diaCal) {
        ActivityWeekRow(calendario.subList(diaCal, diaCal + 7), Modifier)
        diaCal += 7
    }
}
