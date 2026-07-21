package com.angel.gestion_peliculas.ui.screens.peliculas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.angel.gestion_peliculas.model.ActividadData
import com.angel.gestion_peliculas.ui.components.common.PantallaConMenu
import com.angel.gestion_peliculas.model.DistribucionData
import com.angel.gestion_peliculas.model.GeneroData
import com.angel.gestion_peliculas.model.Resena
import com.angel.gestion_peliculas.ui.components.statistics.ActividadCard
import com.angel.gestion_peliculas.ui.components.statistics.DistribucionNotasCard
import com.angel.gestion_peliculas.ui.components.statistics.GenerosFavoritosCard
import com.angel.gestion_peliculas.ui.components.statistics.ResumenGeneralCard
import com.angel.gestion_peliculas.ui.components.statistics.StatSection

@Composable
fun EstadisticasScreen(navController: NavController){
    PantallaConMenu(navController, "Estadisticas",true) {padding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            item{
                StatSection("Resumen") {
                    ResumenGeneralCard(1,8.0,"UP","UP")
                }
            }
            item {
                StatSection("Distribucion de notas") {
                    val lista = listOf(
                        DistribucionData("0-2", 2),
                        DistribucionData("2-4", 1),
                        DistribucionData("4-6", 5),
                        DistribucionData("6-8", 10),
                        DistribucionData("8-10", 7)
                    )
                    DistribucionNotasCard(lista)
                }
            }
            item{
                StatSection("Distribución de generos") {
                    val lista = listOf(
                        GeneroData("Suspense", 5),
                        GeneroData("Terror", 4),
                        GeneroData("Accion", 2),
                        GeneroData("Animacion", 2),
                        GeneroData("Fantasia", 1)
                    )
                    GenerosFavoritosCard(lista)
                }
            }
            item {
                StatSection("Distribución de generos") {
                    val actividad = ActividadData("La lista de Schlinder",
                        "Obsession","Marzo")
                    ActividadCard(actividad)
                }
            }
            item{
                Text("Seccion Ultima Reseña")
            }
        }
    }
}