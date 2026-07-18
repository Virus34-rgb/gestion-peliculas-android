package com.angel.gestion_peliculas.ui.components.common

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun ErrorPopup (cargando : Boolean?, error : String?,context : Context
                ,funcionReset : () -> Unit){
    LaunchedEffect(error){
        if(error!= null && cargando == false){
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
            funcionReset()
        }
    }
}