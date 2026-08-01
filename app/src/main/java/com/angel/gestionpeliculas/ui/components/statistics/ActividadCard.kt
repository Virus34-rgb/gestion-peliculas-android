package com.angel.gestionpeliculas.ui.components.statistics

import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.ActividadData
import java.time.YearMonth

@Composable
fun ActividadCard(
    mes: YearMonth,
    lista: List<ActividadData>?,
    limiteSup: YearMonth?,
    limiteInf: YearMonth?,
    onClickIzq: () -> Unit,
    onClickDer: () -> Unit,
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
    ) {
        Column(
            verticalArrangement = spacedBy(12.dp),
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ActivityHeader(mes, onClickIzq, onClickDer, limiteSup, limiteInf)
            ActivityWeekHeader(Modifier)
            ActivityCalendar(mes, lista)
            LeyendaActividad()
        }
    }
}
