package com.angel.gestionpeliculas.ui.components.common

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
import com.angel.gestionpeliculas.R
import com.angel.gestionpeliculas.network.TokenManager
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaConMenu(
    navController: NavController,
    titulo: String,
    mostrarBottomBar: Boolean,
    content: @Composable (PaddingValues) -> Unit,
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var menuAbierto by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = titulo,
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                actions = {
                    IconButton(onClick = { menuAbierto = true }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Opciones")
                    }
                    DropDown(menuAbierto, { menuAbierto = false }) {
                        menuAbierto = false
                        scope.launch {
                            TokenManager.logout(context)
                            navController.navigate("login") { popUpTo(navController.graph.id) { inclusive = true } }
                        }
                    }
                },
            )
        },
        bottomBar = {
            BottomBar(mostrarBottomBar, navController)
        },
    ) { padding ->
        content(padding)
    }
}

@Composable
private fun DropDown(
    expanded: Boolean,
    onDismissRequest: () -> Unit,
    onClick: () -> Unit,
) {
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = onDismissRequest,
    ) {
        DropdownMenuItem(
            text = { Text("Cerrar sesión") },
            onClick = onClick,
        )
    }
}

@Composable
private fun BottomBar(
    mostrarBottomBar: Boolean,
    navController: NavController,
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
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
                        contentDescription = "Lista",
                    )
                },
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
                        contentDescription = "Buscar",
                    )
                },
            )
            NavigationBarItem(
                selected = currentRoute == "estadisticas",
                onClick = {
                    navController.navigate("estadisticas") {
                        launchSingleTop = true
                        popUpTo(navController.graph.findStartDestination().id) {
                        }
                    }
                },
                icon = {
                    Icon(
                        Icons.Default.BarChart,
                        contentDescription = "Estadisticas",
                    )
                },
            )
        }
    }
}
