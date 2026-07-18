package com.angel.gestion_peliculas.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.angel.gestion_peliculas.model.LoginData
import com.angel.gestion_peliculas.model.Pelicula
import com.angel.gestion_peliculas.model.Resena
import com.angel.gestion_peliculas.model.ResenaRequest
import com.angel.gestion_peliculas.model.TMDBMovie
import com.angel.gestion_peliculas.network.TokenManager
import com.angel.gestion_peliculas.repository.PeliculaRepository
import kotlinx.coroutines.launch

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
    val peliculasTMDB : LiveData<List<TMDBMovie>> = _peliculasTMDB

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
    fun getAll() {
        ejecutar("Error al cargar la lista") {
            val resultado = repository.getAll()
            _resenas.value = resultado
        }
    }
    fun getById(id : Long) {
        ejecutar("Error al cargar la pelicula"){
            val resultado = repository.getById(id)
            _resena.value = resultado
        }
    }
    fun add (r : ResenaRequest) {
        ejecutar("Error al añadir la pelicula"){
            val resultado = repository.add(r)
            _condicion.value = resultado
        }
    }
    fun delete (id : Long){
        ejecutar("Error al borrar la pelicula"){
            val resultado = repository.delete(id)
            _condicion.value = resultado
        }
    }
    fun update (id : Long, r : ResenaRequest){
        ejecutar("Error al actualizar la pelicula"){
            val resultado = repository.update(id,r)
            _condicion.value = resultado
        }
    }
    fun getAllFiltered (filtro : String, ascendente : Boolean){
        ejecutar("Error al filtrar la lista"){
            val resultado = repository.getAllFiltered(filtro,ascendente)
            _resenas.value = resultado
        }
    }
    fun login(data: LoginData, context : Context) {
            ejecutar("Error al hacer login"){
                val resultado = repository.login(data)
                Log.d("ViewModel", "resultado login: $resultado")
                _condicion.value = resultado.first
                if(resultado.first== true){
                    TokenManager.login(context,resultado.second)
                }
                else{
                    _error.value ="Credenciales no válidas, inténtelo de nuevo"
                }
            }
    }

    fun register (data : LoginData){
        ejecutar("Error al hacer register"){
            val resultado = repository.register(data)
            Log.d("ViewModel", "resultado register: $resultado")
            _condicion.value = resultado
            if (resultado == false) {
                _error.value = "El nombre de usuario ya está en uso"
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
    fun resetError(){
        _error.value = null
    }
    fun resetCond(){
        _condicion.value = null
    }
}
