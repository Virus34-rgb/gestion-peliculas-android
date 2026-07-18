package com.angel.gestion_peliculas.ui.components.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun CargandoCirculo(cargando : Boolean?, esperaLarga : Boolean){
    if(cargando == true){
        Box( modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Column(horizontalAlignment = Alignment.CenterHorizontally){
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(8.dp))
                if(esperaLarga){
                    Text(
                        text = "El servidor puede tardar unos segundos en responder...",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(2f)
                    )
                }
            }
        }
    }
}