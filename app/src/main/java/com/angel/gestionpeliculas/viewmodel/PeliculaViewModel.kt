package com.angel.gestionpeliculas.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angel.gestionpeliculas.model.ActividadData
import com.angel.gestionpeliculas.model.DistribucionData
import com.angel.gestionpeliculas.model.EvolucionData
import com.angel.gestionpeliculas.model.GeneroData
import com.angel.gestionpeliculas.model.LoginData
import com.angel.gestionpeliculas.model.RachaData
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.model.ResenaRequest
import com.angel.gestionpeliculas.model.ResumenData
import com.angel.gestionpeliculas.model.SemanaActividad
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.network.TokenManager
import com.angel.gestionpeliculas.repository.PeliculaRepository
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth
import java.time.temporal.WeekFields

class PeliculaViewModel : ViewModel() {
    private val repository = PeliculaRepository()
    private val _resenas = MutableLiveData<List<Resena>>()
    val resenas: LiveData<List<Resena>> = _resenas
    private val _resena = MutableLiveData<Resena?>()
    val resena: LiveData<Resena?> = _resena
    private val _condicion = MutableLiveData<Boolean?>()
    val condicion: LiveData<Boolean?> = _condicion
    private val _cargando = MutableLiveData<Boolean>()
    val cargando = _cargando
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    private val _peliculasTMDB = MutableLiveData<List<TMDBMovie>>()
    val peliculasTMDB: LiveData<List<TMDBMovie>> = _peliculasTMDB
    private val _resumen = MutableLiveData<ResumenData?>()
    val resumen: LiveData<ResumenData?> = _resumen
    private val _notas = MutableLiveData<List<DistribucionData>>()
    val notas: LiveData<List<DistribucionData>> = _notas
    private val _generos = MutableLiveData<List<GeneroData>>()
    val generos: LiveData<List<GeneroData>> = _generos
    private val _actividad = MutableLiveData<List<ActividadData>>()
    val actividad: LiveData<List<ActividadData>> = _actividad
    private val _ultimaResena = MutableLiveData<Resena?>()
    val ultimaResena: LiveData<Resena?> = _ultimaResena
    private val _limiteSup = MutableLiveData<YearMonth?>()
    val limiteSup: LiveData<YearMonth?> = _limiteSup
    private val _limiteInf = MutableLiveData<YearMonth?>()
    val limiteInf: LiveData<YearMonth?> = _limiteInf
    private val _racha = MutableLiveData<RachaData?>()
    val racha: LiveData<RachaData?> = _racha
    private val _evolucion = MutableLiveData<List<EvolucionData>?>()
    val evolucion: LiveData<List<EvolucionData>?> = _evolucion

    private fun ejecutar(
        mensajeError: String,
        accion: suspend () -> Unit,
    ) {
        _cargando.value = true
        viewModelScope.launch {
            try {
                accion()
            } catch (e: Exception) {
                _error.value = mensajeError
            } finally {
                _cargando.value = false
            }
        }
    }

    fun getAll() {
        ejecutar("Error al cargar la lista") {
            val resultado = repository.getAll()
            _resenas.value = resultado
        }
    }

    fun getById(id: Long) {
        ejecutar("Error al cargar la pelicula") {
            val resultado = repository.getById(id)
            _resena.value = resultado
        }
    }

    fun add(r: ResenaRequest) {
        ejecutar("Error al añadir la pelicula") {
            val resultado = repository.add(r)
            _condicion.value = resultado
        }
    }

    fun delete(id: Long) {
        ejecutar("Error al borrar la pelicula") {
            val resultado = repository.delete(id)
            _condicion.value = resultado
        }
    }

    fun update(
        id: Long,
        r: ResenaRequest,
    ) {
        ejecutar("Error al actualizar la pelicula") {
            val resultado = repository.update(id, r)
            _condicion.value = resultado
        }
    }

    fun getAllFiltered(
        filtro: String,
        ascendente: Boolean,
    ) {
        ejecutar("Error al filtrar la lista") {
            val resultado = repository.getAllFiltered(filtro, ascendente)
            _resenas.value = resultado
        }
    }

    fun login(
        data: LoginData,
        context: Context,
    ) {
        ejecutar("Error al hacer login") {
            val resultado = repository.login(data)
            Log.d("ViewModel", "resultado login: $resultado")
            _condicion.value = resultado.first
            if (resultado.first == true) {
                TokenManager.login(context, resultado.second)
            } else {
                _error.value = "Credenciales no válidas, inténtelo de nuevo"
            }
        }
    }

    fun register(data: LoginData) {
        ejecutar("Error al hacer register") {
            val resultado = repository.register(data)
            Log.d("ViewModel", "resultado register: $resultado")
            _condicion.value = resultado
            if (resultado == false) {
                _error.value = "El nombre de usuario ya está en uso"
            }
        }
    }

    fun searchTMDB(query: String) {
        viewModelScope.launch {
            try {
                val resultado = repository.searchTMDB(query)
                _peliculasTMDB.value = resultado
            } catch (e: Exception) {
                Log.d("ViewModel", "error: ${e.message}")
                _error.value = e.message
            }
        }
    }

    fun resetError() {
        _error.value = null
    }

    fun resetCond() {
        _condicion.value = null
    }

    fun cargarEstadisticas(meses: Int) {
        ejecutar("Error al cargar la lista") {
            val resultado = repository.getAll()
            _resenas.value = resultado
            actualizarEstadisticas(resultado, meses)
        }
    }

    private fun actualizarEstadisticas(
        resultado: List<Resena>,
        meses: Int,
    ) {
        _resumen.value = calcularResumen(resultado)
        _notas.value = calcularDistribucionNotas(resultado)
        _generos.value = calcularDistribucionGeneros(resultado)
        _actividad.value = calcularActividad(resultado)
        _ultimaResena.value = getUltimaReseña(resultado)
        _racha.value = calcularRacha(resultado)
        _evolucion.value = calcularEvolucion(resultado, meses)
    }

    private fun calcularResumen(resultado: List<Resena>): ResumenData? {
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

    private fun calcularDistribucionNotas(resultado: List<Resena>): List<DistribucionData> {
        var rango02 = 0
        var rango24 = 0
        var rango46 = 0
        var rango68 = 0
        var rango810 = 0
        for (resena in resultado) {
            val nota = resena.nota
            if (nota < 2) {
                rango02++
            } else if (nota < 4) {
                rango24++
            } else if (nota < 6) {
                rango46++
            } else if (nota < 8) {
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

    private fun calcularDistribucionGeneros(resultado: List<Resena>): List<GeneroData> {
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

    private fun calcularActividad(resultado: List<Resena>): List<ActividadData> {
        val mapa = mutableMapOf<LocalDate, Int>()
        for (resena in resultado) {
            val fecha = resena.fechaPublicacion ?: continue
            mapa[fecha] = mapa.getOrDefault(fecha, 0) + 1
        }
        _limiteSup.value = YearMonth.now()
        _limiteInf.value =
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

    private fun getUltimaReseña(resultado: List<Resena>): Resena? =
        resultado.maxByOrNull { resena ->
            resena.resenaId ?: 0L
        }

    private fun calcularRacha(resultado: List<Resena>): RachaData? {
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

    private fun calcularEvolucion(
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

    private fun semanaAnterior(semana: SemanaActividad): SemanaActividad {
        if (semana.semana == 1) {
            val ultimaSemana =
                LocalDate
                    .of(semana.anio - 1, 12, 28)
                    .get(WeekFields.ISO.weekOfWeekBasedYear())

            return SemanaActividad(semana.anio - 1, ultimaSemana)
        }
        return SemanaActividad(
            semana.anio,
            semana.semana - 1,
        )
    }

    private fun esSemanaSiguiente(
        anterior: SemanaActividad,
        siguiente: SemanaActividad,
    ): Boolean = semanaAnterior(siguiente) == anterior
}
