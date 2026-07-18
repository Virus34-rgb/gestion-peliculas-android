package com.angel.gestion_peliculas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.angel.gestion_peliculas.network.TokenManager
import com.angel.gestion_peliculas.ui.screens.peliculas.ActualizarScreen
import com.angel.gestion_peliculas.ui.screens.peliculas.DetalleScreen
import com.angel.gestion_peliculas.ui.screens.peliculas.ListaScreen
import com.angel.gestion_peliculas.ui.screens.peliculas.AnadirScreen
import com.angel.gestion_peliculas.ui.screens.auth.LoginScreen
import com.angel.gestion_peliculas.ui.theme.GestionPeliculasTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import com.angel.gestion_peliculas.ui.screens.peliculas.BuscarScreen
import com.angel.gestion_peliculas.ui.screens.auth.RegisterScreen
import com.angel.gestion_peliculas.ui.screens.resenas.ReseñasPeliculaScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GestionPeliculasTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                var startDestinationX by remember { mutableStateOf<String?>(null) }
                val scope = rememberCoroutineScope()
                LaunchedEffect(Unit) {
                    TokenManager.loadFromDataStore(context)
                    if (TokenManager.haySesionValida()) {
                        startDestinationX = "lista"
                    } else {
                        TokenManager.logout(context)
                        startDestinationX = "login"
                    }
                }
                LaunchedEffect(Unit) {
                    TokenManager.onSesionExpirada = {
                        scope.launch {
                            TokenManager.logout(context)
                            navController.navigate("login") {
                                popUpTo(0) { inclusive = true }
                            }
                        }
                    }
                }

                if (startDestinationX == null) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                } else {
                    NavHost(
                        navController = navController,
                        startDestination = startDestinationX!!,
                        modifier = Modifier.safeDrawingPadding()
                    ) {
                        composable("login") { LoginScreen(navController) }
                        composable("registro") { RegisterScreen(navController) }
                        composable("lista") { ListaScreen(navController) }
                        composable("detalle/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toLong()
                            DetalleScreen(navController, resenaId = id,tmdbId = null)
                        }
                        composable("anadir") { AnadirScreen(navController) }
                        composable("actualizar/{id}") { backStackEntry ->
                            val id = backStackEntry.arguments?.getString("id")?.toLong()
                            ActualizarScreen(navController, id!!)
                        }
                        composable("buscar") { BuscarScreen(navController) }
                        composable("detalleCatalogo/{tmdbId}") { backStackEntry ->
                            val tmdbId = backStackEntry.arguments?.getString("tmdbId")?.toLong()
                            DetalleScreen(navController, resenaId = null, tmdbId = tmdbId)
                        }
                        composable("resenasPelicula/{peliculaId}") { backStackEntry ->
                            val peliculaId = backStackEntry.arguments?.getString("peliculaId")?.toLong()
                            ReseñasPeliculaScreen(navController, peliculaId!!)
                        }
                    }
                }
            }
        }
    }
}


