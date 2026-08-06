package com.angel.gestionpeliculas.ui.components.pelicula.ticket

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

@Composable
fun TicketTexture(modifier: Modifier = Modifier) {
    val random = remember { Random(42) }
    val grainPoints =
        remember {
            List(250) {
                Offset(
                    random.nextFloat(),
                    random.nextFloat(),
                )
            }
        }
    val fibers =
        remember {
            List(35) {
                Pair(
                    random.nextFloat(),
                    random.nextFloat(),
                )
            }
        }
    /*val stains = remember {
        List(8) {
            Triple(
                random.nextFloat(),
                random.nextFloat(),
                random.nextFloat()
            )
        }
    }*/
    Canvas(modifier = modifier) {
        grainPoints.forEach { point ->
            drawCircle(
                color = Color(0xFF8A6B3F).copy(alpha = 0.35f),
                radius = 1.2f,
                center =
                    Offset(
                        point.x * size.width,
                        point.y * size.height,
                    ),
            )
        }
        fibers.forEach { fiber ->
            val startX = fiber.first * size.width
            val startY = fiber.second * size.height
            drawLine(
                color = Color(0xFF8A6B3F).copy(alpha = 0.095f),
                start = Offset(startX, startY),
                end =
                    Offset(
                        startX + size.width * 0.15f,
                        startY + size.height * 0.01f,
                    ),
                strokeWidth = 1f,
            )
        }
        /*
        stains.forEach { stain ->
            drawCircle(
                color = Color(0xFF9A7845).copy(alpha = 0.05f),
                radius = size.width * (0.02f + stain.third * 0.03f),
                center = Offset(
                    stain.first * size.width,
                    stain.second * size.height,
                ),
                style = Stroke(width = 8f),
            )
        }*/
    }
}
