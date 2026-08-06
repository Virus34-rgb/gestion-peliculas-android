package com.angel.gestionpeliculas.model.parametros

data class FormularioResenaRapidaData(
    val nota: Float,
    val onNotaChange: (Float) -> Unit,
    val opinion: String?,
    val onOpinionChange: (String) -> Unit,
    val enabled: Boolean,
    val onGuardar: () -> Unit,
)
