package com.angel.gestionpeliculas.ui.components.pelicula.ticket

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.Absolute.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.angel.gestionpeliculas.model.Resena
import com.angel.gestionpeliculas.ui.components.pelicula.ImagenPath
import com.angel.gestionpeliculas.ui.components.pelicula.NotaEstrella
import com.angel.gestionpeliculas.ui.components.pelicula.TicketBorderWidth
import com.angel.gestionpeliculas.ui.theme.TicketColors

@Composable
fun TicketPelicula(
    resena: Resena,
    cargando: Boolean?,
    onClick: () -> Unit,
) {
    Box {
        TicketBorder(Modifier.matchParentSize())

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .padding(TicketBorderWidth)
                .clickable(enabled = cargando != true) { onClick() },
            shape = RoundedCornerShape(16.dp),
            shadowElevation = 6.dp,
            color = TicketColors.Background,
        ) {
            TicketBackground() {
                TicketTexture(Modifier.matchParentSize())
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.Top,
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    LeftSection(
                        resena,
                        Modifier
                            .weight(1f)
                            .padding(16.dp),
                    )
                    TicketSeparator(
                        modifier = Modifier
                            .height(100.dp)
                            .align(Alignment.CenterVertically),
                    )
                    RightSection(
                        resena,
                        Modifier
                            .width(65.dp)
                            .height(90.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun LeftSection(
    resena: Resena,
    modifier: Modifier = Modifier,
) {
    Row(horizontalArrangement = spacedBy(8.dp), modifier = modifier) {
        ImagenPath(
            resena.posterPath,
            resena.nombre,
            Modifier
                .width(60.dp)
                .height(90.dp),
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            Text(
                text = resena.nombre,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = TicketColors.Title,
            )
            Text(
                text = resena.generos.joinToString(", "),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                color = TicketColors.Body,
            )
            Text(
                text = resena.fecha.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = TicketColors.Body,
            )
        }
    }
}

@Composable
private fun RightSection(
    resena: Resena,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
    ) {
        NotaEstrella(resena.nota, 10.dp, Modifier.align(Alignment.TopEnd))
        TicketBarcode(
            Modifier
                .width(40.dp)
                .height(50.dp)
                .align(Alignment.BottomCenter),
        )
    }
}