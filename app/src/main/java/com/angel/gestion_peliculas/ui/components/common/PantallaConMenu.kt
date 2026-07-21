package com.angel.gestion_peliculas.ui.components.common

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.angel.gestion_peliculas.R
import com.angel.gestion_peliculas.network.TokenManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConMenu(
    navController: NavController,
    titulo: String,
    mostrarBottomBar: Boolean,
    content: @Composable (PaddingValues) -> Unit
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    var menuAbierto by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = titulo,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { menuAbierto = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Opciones")
                    }
                    DropdownMenu(
                        expanded = menuAbierto,
                        onDismissRequest = { menuAbierto = false }) {
                        DropdownMenuItem(
                            text = { Text("Cerrar sesión") },
                            onClick = {
                                menuAbierto = false;
                                scope.launch {
                                    TokenManager.logout(context);
                                    navController.navigate("login")
                                    { popUpTo(navController.graph.id) { inclusive = true } }
                                }
                            })
                    }
                }
            )
        },
        bottomBar = {
            if (mostrarBottomBar) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == "lista",
                        onClick = {
                            navController.navigate("lista") {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                painterResource(R.drawable.list_add),
                                contentDescription = "Lista"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = currentRoute == "buscar",
                        onClick = {
                            navController.navigate("buscar") {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        },
                        icon = {
                            Icon(
                                painterResource(R.drawable.search_item),
                                contentDescription = "Buscar"
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = currentRoute == "estadisticas",
                        onClick = {
                            navController.navigate("estadisticas") {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                            }
                        },
                        icon = {
                            Icon(Icons.Default.BarChart,
                                contentDescription = "Estadisticas")
                        }
                    )
                }
            }
        }

    ) { padding ->
        content(padding)
    }
}