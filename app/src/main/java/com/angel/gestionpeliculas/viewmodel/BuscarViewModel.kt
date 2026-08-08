package com.angel.gestionpeliculas.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angel.gestionpeliculas.model.CountryProviders
import com.angel.gestionpeliculas.model.PeliculaResponse
import com.angel.gestionpeliculas.model.ResenaPublicaResponse
import com.angel.gestionpeliculas.model.ResenaRequest
import com.angel.gestionpeliculas.model.TMDBCollection
import com.angel.gestionpeliculas.model.TMDBCredits
import com.angel.gestionpeliculas.model.TMDBMovie
import com.angel.gestionpeliculas.model.TMDBSimilarResponse
import com.angel.gestionpeliculas.model.TMDBVideoResponse
import com.angel.gestionpeliculas.repository.PeliculaRepository
import com.angel.gestionpeliculas.repository.TMDBRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BuscarViewModel : ViewModel() {
    private val repository = TMDBRepository()
    private val pRepository = PeliculaRepository()
    private val _peliculaResponse = MutableStateFlow<PeliculaResponse?>(null)
    val peliculaResponse: StateFlow<PeliculaResponse?> = _peliculaResponse
    private val _resenas = MutableStateFlow<List<ResenaPublicaResponse>>(emptyList())
    val resenas: StateFlow<List<ResenaPublicaResponse>> = _resenas
    private val _peliculasTMDB = MutableStateFlow<List<TMDBMovie>>(emptyList())
    val peliculasTMDB: StateFlow<List<TMDBMovie>> = _peliculasTMDB
    private val _detalleTMDB = MutableStateFlow<TMDBMovie?>(null)
    val detalleTMDB: StateFlow<TMDBMovie?> = _detalleTMDB
    private val _watchProviders = MutableStateFlow<CountryProviders?>(null)
    val watchProviders: StateFlow<CountryProviders?> = _watchProviders
    private val _videos = MutableStateFlow<TMDBVideoResponse?>(null)
    val videos: StateFlow<TMDBVideoResponse?> = _videos
    private val _credits = MutableStateFlow<TMDBCredits?>(null)
    val credits: StateFlow<TMDBCredits?> = _credits
    private val _similar = MutableStateFlow<TMDBSimilarResponse?>(null)
    val similar: StateFlow<TMDBSimilarResponse?> = _similar
    private val _collection = MutableStateFlow<TMDBCollection?>(null)
    val collection: StateFlow<TMDBCollection?> = _collection
    private val _cargando = MutableStateFlow<Boolean>(false)
    val cargando: StateFlow<Boolean> = _cargando
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _condicion = MutableStateFlow<Boolean?>(false)
    val condicion: StateFlow<Boolean?> = _condicion

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
                Log.e("BuscarViewModel", mensajeError, e)
                _error.value = mensajeError
            } finally {
                _cargando.value = false
            }
        }
    }

    @Suppress("TooGenericExceptionCaught")
    private fun ejecutarTMDB(
        mensajeError: String,
        accion: suspend () -> Unit,
    ) {
        viewModelScope.launch {
            try {
                accion()
            } catch (e: Exception) {
                Log.d("ViewModel", mensajeError, e)
                _error.value = mensajeError
            }
        }
    }

    fun searchTMDB(query: String) {
        ejecutarTMDB("Error al buscar pelicula en TMDB") {
            val resultado = repository.searchTMDB(query)
            _peliculasTMDB.value = resultado
        }
    }

    fun buscarPorTMDB(tmdbId: Long) {
        ejecutar("Error al buscar la pelicula en TMDB") {
            Log.d("TMDB", "Buscando película $tmdbId")

            val detalle = repository.getMovieDetail(tmdbId)
            Log.d("TMDB", "Detalle = $detalle")

            _detalleTMDB.value = detalle

            val resultado = pRepository.buscarPorTmdb(tmdbId)
            Log.d("TMDB", "Resultado BD = $resultado")

            _peliculaResponse.value = resultado

            if (resultado != null) {
                _resenas.value = pRepository.resenasDePelicula(resultado.peliculaId)
            } else {
                _resenas.value = emptyList()
            }

            _watchProviders.value = repository.getWatchProviders(tmdbId)
        }
    }

    fun addResenaRapido(r: ResenaRequest) {
        ejecutar("Error al añadir la pelicula") {
            val resultado = pRepository.add(r)
            _condicion.value = resultado
        }
    }

    fun updateResenaRapido(
        id: Long,
        r: ResenaRequest,
    ) {
        ejecutar("Error al actualizar la pelicula") {
            val resultado = pRepository.update(id, r)
            _condicion.value = resultado
        }
    }

    fun cargarResenasDePelicula(peliculaId: Long) {
        ejecutar("Error al cargar las reseñas") {
            _resenas.value = pRepository.resenasDePelicula(peliculaId)
        }
    }

    fun getAllExtras(peliculaId: Long) {
        getVideos(peliculaId)
        getCredits(peliculaId)
        getSimilar(peliculaId)
        getCollection(peliculaId)
    }

    private fun getVideos(peliculaId: Long) {
        ejecutarTMDB("Error al buscar videos en TMDB") {
            val resultado = repository.getVideos(peliculaId)
            _videos.value = resultado
        }
    }

    private fun getCredits(peliculaId: Long) {
        ejecutarTMDB("Error al buscar los créditos en TMDB") {
            val resultado = repository.getCredits(peliculaId)
            _credits.value = resultado
        }
    }

    private fun getSimilar(peliculaId: Long) {
        ejecutarTMDB("Error al buscar peliculas similares en TMDB") {
            val resultado = repository.getSimilar(peliculaId)
            _similar.value = resultado
        }
    }

    private fun getCollection(peliculaId: Long) {
        ejecutarTMDB("Error al buscar la collecion en TMDB") {
            val resultado = repository.getCollection(peliculaId)
            _collection.value = resultado
        }
    }

    fun resetError() {
        _error.value = null
    }

    fun resetCond() {
        _condicion.value = null
    }

    fun limpiarBusqueda() {
        _peliculasTMDB.value = emptyList()
    }
}
