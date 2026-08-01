package com.angel.gestionpeliculas.ui.screens.auth

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.angel.gestionpeliculas.model.LoginData
import com.angel.gestionpeliculas.ui.components.auth.AuthScreen
import com.angel.gestionpeliculas.ui.components.auth.Contrasena
import com.angel.gestionpeliculas.ui.components.common.BotonPrincipal
import com.angel.gestionpeliculas.viewmodel.PeliculaViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: PeliculaViewModel = viewModel()
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var pass2 by remember { mutableStateOf("") }
    val condicion by viewModel.condicion.observeAsState()
    val error by viewModel.error.observeAsState()
    val cargando by viewModel.cargando.observeAsState()
    val context = LocalContext.current
    LaunchedEffect(condicion) {
        if (condicion == true) {
            viewModel.resetCond()
            Toast
                .makeText(
                    context,
                    "Registro completado, ya puedes iniciar sesión",
                    Toast.LENGTH_SHORT,
                ).show()
            navController.popBackStack()
        }
    }
    AuthScreen("Crear tu cuenta", cargando, error, viewModel::resetError) {
        OutlinedTextField(
            enabled = cargando != true,
            value = user,
            onValueChange = { user = it },
            label = { Text("Nombre de Usuario") },
        )
        Contrasena(pass, { pass = it }, "Contraseña", cargando)
        Contrasena(pass2, { pass2 = it }, "Confirmar Contraseña", cargando)
        BotonPrincipal(
            "Registrar",
            cargando != true &&
                user.isNotBlank() &&
                pass.isNotBlank() &&
                pass2.isNotBlank(),
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            if (pass == pass2) {
                viewModel.register(LoginData(user, pass))
            } else {
                Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
