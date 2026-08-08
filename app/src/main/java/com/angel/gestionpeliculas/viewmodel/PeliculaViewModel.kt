package com.angel.gestionpeliculas.viewmodel

import android.content.Context
import android.util.Log
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
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.network.TokenManager
import com.angel.gestionpeliculas.repository.PeliculaRepository
import com.angel.gestionpeliculas.repository.TMDBRepository
import com.angel.gestionpeliculas.statistics.calcularActividad
import com.angel.gestionpeliculas.statistics.calcularDistribucionGeneros
import com.angel.gestionpeliculas.statistics.calcularDistribucionNotas
import com.angel.gestionpeliculas.statistics.calcularEvolucion
import com.angel.gestionpeliculas.statistics.calcularRacha
import com.angel.gestionpeliculas.statistics.calcularResumen
import com.angel.gestionpeliculas.statistics.getUltimaReseña
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.time.YearMonth

class PeliculaViewModel : ViewModel() {
    private val repository = PeliculaRepository()
    private val tRepository = TMDBRepository()
    private val _resenas = MutableStateFlow<List<Resena>>(emptyList())
    val resenas: StateFlow<List<Resena>> = _resenas
    private val _resena = MutableStateFlow<Resena?>(null)
    val resena: StateFlow<Resena?> = _resena
    private val _condicion = MutableStateFlow<Boolean?>(null)
    val condicion: StateFlow<Boolean?> = _condicion
    private val _cargando = MutableStateFlow(false)
    val cargando: StateFlow<Boolean> = _cargando
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _peliculasTMDB = MutableStateFlow<List<TMDBMovie>>(emptyList())
    val peliculasTMDB: StateFlow<List<TMDBMovie>> = _peliculasTMDB
    private val _resumen = MutableStateFlow<ResumenData?>(null)
    val resumen: StateFlow<ResumenData?> = _resumen
    private val _notas = MutableStateFlow<List<DistribucionData>>(emptyList())
    val notas: StateFlow<List<DistribucionData>> = _notas
    private val _generos = MutableStateFlow<List<GeneroData>>(emptyList())
    val generos: StateFlow<List<GeneroData>> = _generos
    private val _actividad = MutableStateFlow<List<ActividadData>>(emptyList())
    val actividad: StateFlow<List<ActividadData>> = _actividad
    private val _ultimaResena = MutableStateFlow<Resena?>(null)
    val ultimaResena: StateFlow<Resena?> = _ultimaResena
    private val _racha = MutableStateFlow<RachaData?>(null)
    val racha: StateFlow<RachaData?> = _racha
    private val _evolucion = MutableStateFlow<List<EvolucionData>?>(null)
    val evolucion: StateFlow<List<EvolucionData>?> = _evolucion
    private val _limiteSup = MutableStateFlow<YearMonth?>(null)
    val limiteSup: StateFlow<YearMonth?> = _limiteSup
    private val _limiteInf = MutableStateFlow<YearMonth?>(null)
    val limiteInf: StateFlow<YearMonth?> = _limiteInf

    @Suppress("TooGenericExceptionCaught")
    private fun ejecutar(
        mensajeError: String,
        accion: suspend () -> Unit,
    ) {
        _cargando.value = true
        viewModelScope.launch {
            try {
                accion()
            } catch (e: Exception) {
                Log.e("PeliculaViewModel", mensajeError, e)
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
                val resultado = tRepository.searchTMDB(query)
                _peliculasTMDB.value = resultado
            } catch (e: IOException) {
                Log.d("ViewModel", "Error de red", e)
                _error.value = "Error de conexión"
            } catch (e: HttpException) {
                Log.d("ViewModel", "Error HTTP ${e.code()}", e)
                _error.value = "Error del servidor"
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
        _actividad.value = calcularActividad(resultado, _limiteSup, _limiteInf)
        _ultimaResena.value = getUltimaReseña(resultado)
        _racha.value = calcularRacha(resultado)
        _evolucion.value = calcularEvolucion(resultado, meses)
    }
}
