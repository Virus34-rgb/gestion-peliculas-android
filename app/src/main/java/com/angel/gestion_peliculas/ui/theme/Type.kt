package com.angel.gestion_peliculas.ui.theme

import com.angel.gestion_peliculas.R
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Fuente para títulos: Cinzel
val CinzelFontFamily = FontFamily(
    Font(R.font.cinzel_regular, FontWeight.Normal),
    Font(R.font.cinzel_medium, FontWeight.Medium),
    Font(R.font.cinzel_semibold, FontWeight.SemiBold), // revisa el nombre real del archivo
    Font(R.font.cinzel_bold, FontWeight.Bold),
    Font(R.font.cinzel_extrabold, FontWeight.ExtraBold),
    Font(R.font.cinzel_black, FontWeight.Black)
)

// Fuente para cuerpo: Nunito Sans (variable)
val NunitoSansFontFamily = FontFamily(
    Font(R.font.nunito_sans, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.nunito_sans, FontWeight.Medium, FontStyle.Normal),
    Font(R.font.nunito_sans, FontWeight.SemiBold, FontStyle.Normal),
    Font(R.font.nunito_sans, FontWeight.Bold, FontStyle.Normal),
    Font(R.font.nunito_sans_italic, FontWeight.Normal, FontStyle.Italic)
)

val Typography = Typography(
    // ---- Títulos (Cinzel) ----
    displayLarge = TextStyle(
        fontFamily = CinzelFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 36.sp,
        lineHeight = 44.sp,
        letterSpacing = 0.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = CinzelFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),
    titleLarge = TextStyle(
        fontFamily = CinzelFontFamily,
        fontWeight = FontWeight.SemiBold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    titleMedium = TextStyle(
        fontFamily = CinzelFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.15.sp
    ),

    // ---- Cuerpo / etiquetas (Nunito Sans) ----
    bodyLarge = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    bodySmall = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.4.sp
    ),
    labelLarge = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
    ),
    labelMedium = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    labelSmall = TextStyle(
        fontFamily = NunitoSansFontFamily,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)