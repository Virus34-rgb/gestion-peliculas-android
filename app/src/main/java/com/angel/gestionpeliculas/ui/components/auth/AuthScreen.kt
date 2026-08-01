package com.angel.gestionpeliculas.ui.components.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.ui.components.common.BaseScreen

@Composable
fun AuthScreen(
    titulo: String,
    loading: Boolean?,
    error: String?,
    onErrorReset: () -> Unit,
    content: @Composable ColumnScope.() -> Unit,
) {
    BaseScreen(loading, error, onErrorReset) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    AuthHeader(titulo)
                    AuthContainer {
                        content()
                    }
                }
            }
        }
    }
}
