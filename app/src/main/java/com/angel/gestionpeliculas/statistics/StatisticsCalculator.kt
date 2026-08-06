package com.angel.gestionpeliculas.statistics

import androidx.lifecycle.MutableLiveData
import com.angel.gestionpeliculas.model.ActividadData
import com.angel.gestionpeliculas.model.DistribucionData
import com.angel.gestionpeliculas.model.EvolucionData
import com.angel.gestionpeliculas.model.GeneroData
import com.angel.gestionpeliculas.model.RachaData
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.model.ResumenData
import com.angel.gestionpeliculas.model.SemanaActividad
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields

fun calcularResumen(resultado: List<Resena>): ResumenData? {
    if (resultado.isEmpty()) {
        return ResumenData(0, 0.0, "", "")
    }
    var total: Double = 0.0
    val cantidad = resultado.size
    for (resena in resultado) {
        total += resena.nota
    }
    val notaMedia = total / cantidad
    val mejor = resultado.maxByOrNull { it.nota }?.nombre
    val peor = resultado.minByOrNull { it.nota }?.nombre
    return ResumenData(cantidad, notaMedia, mejor!!, peor!!)
}

fun calcularDistribucionNotas(resultado: List<Resena>): List<DistribucionData> {
    var rango02 = 0
    var rango24 = 0
    var rango46 = 0
    var rango68 = 0
    var rango810 = 0
    val notas = listOf(2, 4, 6, 8)
    for (resena in resultado) {
        val nota = resena.nota
        if (nota < notas[0]) {
            rango02++
        } else if (nota < notas[1]) {
            rango24++
        } else if (nota < notas[2]) {
            rango46++
        } else if (nota < notas[3]) {
            rango68++
        } else {
            rango810++
        }
    }
    return listOf(
        DistribucionData("0-2", rango02),
        DistribucionData("2-4", rango24),
        DistribucionData("4-6", rango46),
        DistribucionData("6-8", rango68),
        DistribucionData("8-10", rango810),
    )
}

fun calcularDistribucionGeneros(resultado: List<Resena>): List<GeneroData> {
    val mapa = mutableMapOf<String, Int>()
    for (resenas in resultado) {
        for (genero in resenas.generos) {
            if (mapa.contains(genero)) {
                mapa[genero] = mapa.getValue(genero) + 1
            } else {
                mapa.put(genero, 1)
            }
        }
    }
    val lista =
        mapa.map { (genero, cantidad) ->
            GeneroData(genero, cantidad)
        }
    return lista
}

fun calcularActividad(
    resultado: List<Resena>,
    limiteSup: MutableLiveData<YearMonth?>,
    limiteInf: MutableLiveData<YearMonth?>,
): List<ActividadData> {
    val mapa = mutableMapOf<LocalDate, Int>()
    for (resena in resultado) {
        val fecha = resena.fechaPublicacion ?: continue
        mapa[fecha] = mapa.getOrDefault(fecha, 0) + 1
    }
    limiteSup.value = YearMonth.now()
    limiteInf.value =
        YearMonth.from(
            resultado.minByOrNull { it.fechaPublicacion!! }!!.fechaPublicacion,
        )
    return mapa
        .map { (fecha, cantidad) ->
            ActividadData(
                fecha,
                cantidad,
            )
        }.sortedBy { it.fecha }
}

fun getUltimaReseña(resultado: List<Resena>): Resena? =
    resultado.maxByOrNull { resena ->
        resena.resenaId ?: 0L
    }

fun calcularRacha(resultado: List<Resena>): RachaData? {
    if (resultado.isEmpty()) return null
    val semanas =
        resultado
            .map {
                val fecha = it.fechaPublicacion
                SemanaActividad(
                    fecha!!.get(WeekFields.ISO.weekBasedYear()),
                    fecha.get(WeekFields.ISO.weekOfWeekBasedYear()),
                )
            }.distinct()
            .sortedWith(compareBy({ it.anio }, { it.semana }))
    val semanasSet = semanas.toSet()
    // Racha actual
    var actual = 0
    var semana =
        SemanaActividad(
            LocalDate.now().get(WeekFields.ISO.weekBasedYear()),
            LocalDate.now().get(WeekFields.ISO.weekOfWeekBasedYear()),
        )
    while (semanasSet.contains(semana)) {
        actual++
        semana = semanaAnterior(semana)
    }
    // Racha máxima histórica
    var maxima = 0
    var contador = 1
    for (i in 1 until semanas.size) {
        if (esSemanaSiguiente(semanas[i - 1], semanas[i])) {
            contador++
        } else {
            maxima = maxOf(maxima, contador)
            contador = 1
        }
    }
    maxima = maxOf(maxima, contador)
    return RachaData(actual, maxima)
}

fun calcularEvolucion(
    resultado: List<Resena>,
    meses: Int,
): List<EvolucionData>? {
    if (resultado.isEmpty()) return null
    val lista = mutableListOf<EvolucionData>()
    for (i in 0 until meses) {
        val mes = YearMonth.now().minusMonths(i.toLong())
        var notas = 0.0
        var cantidad = 0
        for (resena in resultado) {
            val mesResena = YearMonth.from(resena.fechaPublicacion)

            if (mesResena == mes) {
                notas += resena.nota
                cantidad++
            }
        }
        lista.add(
            EvolucionData(
                mes.year,
                mes.monthValue,
                cantidad,
                if (cantidad > 0) notas / cantidad else 0.0,
            ),
        )
    }
    return lista.reversed()
}

fun semanaAnterior(semana: SemanaActividad): SemanaActividad {
    val mes = 12
    val dia = 28
    val uno = 1
    if (semana.semana == uno) {
        val ultimaSemana =
            LocalDate
                .of(semana.anio - uno, mes, dia)
                .get(WeekFields.ISO.weekOfWeekBasedYear())

        return SemanaActividad(semana.anio - uno, ultimaSemana)
    }
    return SemanaActividad(
        semana.anio,
        semana.semana - uno,
    )
}

fun esSemanaSiguiente(
    anterior: SemanaActividad,
    siguiente: SemanaActividad,
): Boolean = semanaAnterior(siguiente) == anterior
