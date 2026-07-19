package com.angel.gestion_peliculas.ui.screens.auth

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.angel.gestion_peliculas.model.LoginData
import com.angel.gestion_peliculas.ui.components.auth.AuthScreen
import com.angel.gestion_peliculas.ui.components.auth.Contrasena
import com.angel.gestion_peliculas.ui.components.common.BotonPrincipal
import com.angel.gestion_peliculas.viewmodel.PeliculaViewModel

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: PeliculaViewModel = viewModel()
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val error by viewModel.error.observeAsState()
    val cargando by viewModel.cargando.observeAsState()
    val context = LocalContext.current
    val condicion by viewModel.condicion.observeAsState()
    LaunchedEffect(condicion) {
        if (condicion == true) {
            viewModel.resetCond()
            navController.navigate("lista") {
                popUpTo("login") { inclusive = true }
            }
        }
    }
    AuthScreen("Accede a tu coleción de reseñas", cargando, error, viewModel::resetError) {
        OutlinedTextField(
            enabled = cargando != true,
            value = user,
            onValueChange = { user = it },
            label = { Text("Nombre de Usuario") }
        )
        Contrasena(pass, { pass = it }, "Contraseña", cargando)
        BotonPrincipal(
            "Iniciar Sesion",
            cargando != true && user.isNotBlank() && pass.isNotBlank(),
            Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            viewModel.login(LoginData(user, pass), context)
        }
        TextButton(onClick = { navController.navigate("registro") }) {
            Text(
                text = "¿No tienes cuenta? Regístrate",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}