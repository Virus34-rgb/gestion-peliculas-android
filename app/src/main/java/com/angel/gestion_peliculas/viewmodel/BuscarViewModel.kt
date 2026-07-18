package com.angel.gestion_peliculas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angel.gestion_peliculas.model.CountryProviders
import com.angel.gestion_peliculas.model.PeliculaResponse
import com.angel.gestion_peliculas.model.ResenaPublicaResponse
import com.angel.gestion_peliculas.model.ResenaRequest
import com.angel.gestion_peliculas.model.TMDBMovie
import com.angel.gestion_peliculas.repository.PeliculaRepository
import kotlinx.coroutines.launch

class BuscarViewModel : ViewModel() {
    private val repository = PeliculaRepository()
    private val _peliculaResponse = MutableLiveData<PeliculaResponse?>()
    val peliculaResponse : LiveData<PeliculaResponse?> = _peliculaResponse
    private val _resenas = MutableLiveData<List<ResenaPublicaResponse>>()
    val resenas : LiveData<List<ResenaPublicaResponse>> = _resenas
    private val _peliculasTMDB = MutableLiveData<List<TMDBMovie>>()
    val peliculasTMDB : LiveData<List<TMDBMovie>> = _peliculasTMDB
    private val _detalleTMDB = MutableLiveData<TMDBMovie?>()
    val detalleTMDB: LiveData<TMDBMovie?> = _detalleTMDB
    private val _watchProviders = MutableLiveData<CountryProviders?>()
    val watchProviders : LiveData<CountryProviders?> = _watchProviders
    private val _cargando = MutableLiveData<Boolean>()
    val cargando = _cargando
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error
    private val _condicion = MutableLiveData<Boolean?>()
    val condicion: LiveData<Boolean?> = _condicion
    private fun ejecutar(mensajeError : String, accion : suspend () -> Unit ) {
        _cargando.value = true
        viewModelScope.launch {
            try{
                accion()
            }
            catch(e: Exception){
                _error.value = mensajeError
            }
            finally {
                _cargando.value= false
            }
        }
    }
    fun searchTMDB(query : String){
        viewModelScope.launch(){
            try {
                val resultado = repository.searchTMDB(query)
                _peliculasTMDB.value = resultado
            }
            catch (e: Exception) {
                Log.d("ViewModel", "error: ${e.message}")
                _error.value = e.message
            }
        }
    }
    fun buscarPorTMDB(tmdbId: Long) {
        ejecutar("Error al buscar la pelicula en TMDB") {

            Log.d("TMDB", "Buscando película $tmdbId")

            val detalle = repository.getMovieDetail(tmdbId, "es-ES")
            Log.d("TMDB", "Detalle = $detalle")

            _detalleTMDB.value = detalle

            val resultado = repository.buscarPorTmdb(tmdbId)
            Log.d("TMDB", "Resultado BD = $resultado")

            _peliculaResponse.value = resultado

            if (resultado != null) {
                _resenas.value = repository.resenasDePelicula(resultado.peliculaId)
            } else {
                _resenas.value = emptyList()
            }

            _watchProviders.value = repository.getWatchProviders(tmdbId)
        }
    }
    fun addResenaRapido (r: ResenaRequest){
        ejecutar("Error al añadir la pelicula"){
            val resultado = repository.add(r)
            _condicion.value = resultado
        }
    }
    fun updateResenaRapido(id: Long, r: ResenaRequest){
        ejecutar("Error al actualizar la pelicula"){
            val resultado = repository.update(id,r)
            _condicion.value = resultado
        }
    }
    fun cargarResenasDePelicula(peliculaId: Long) {
        ejecutar("Error al cargar las reseñas") {
            _resenas.value = repository.resenasDePelicula(peliculaId)
        }
    }
    fun resetError(){
        _error.value = null
    }
    fun resetCond(){
        _condicion.value = null
    }
    fun limpiarBusqueda() {
        _peliculasTMDB.value = emptyList()
    }
}