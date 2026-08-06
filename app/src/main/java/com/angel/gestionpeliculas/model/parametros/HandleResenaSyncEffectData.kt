package com.angel.gestionpeliculas.model.parametros

import com.angel.gestionpeliculas.model.Resena
import java.time.LocalDate

data class HandleResenaSyncEffectData(
    val resena: Resena?,
    val onNombre: (String) -> Unit,
    val onNota: (Float) -> Unit,
    val onGeneros: (List<String>) -> Unit,
    val onFecha: (LocalDate?) -> Unit,
    val onPoster: (String?) -> Unit,
    val onOpinion: (String?) -> Unit,
    val onTmdbId: (Long?) -> Unit,
)
