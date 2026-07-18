package com.angel.gestion_peliculas.ui.components.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AuthContainer(contenido : @Composable () -> Unit){ //como pongo el parametro contenido?
    ElevatedCard(elevation = CardDefaults.cardElevation(4.dp,)
        , modifier = Modifier.fillMaxWidth()){//Como ajusto la elevacion?
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)){
            contenido()
        }
    }
}