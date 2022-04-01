package com.example.customtheming.ui.mytheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customtheming.R

/**
 * A fully customized theme to replace material theme
 * Features:
 * - Custom type, color and shape systems
 * - Custom elevation
 * - Custom text selection colors
 *  Note that all material components need to be replaced with custom components
 *  to get the desired look and feel
 */

val MyColorSystem.borderColor: Color
    @Composable get() = if(isSystemInDarkTheme()) Color(0xFF388E3C) else Color(0xFFC2185B)

@Composable
fun NewTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val shapeSystem = MyShapeSystem(
        round = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
        cut = CutCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
    )

    val typeSystem = MyTypeSystem(
        defaultFontFamily = FontFamily(Font(R.font.gochihand)),
        tiny = TextStyle(fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.gochihand))),
        medium = TextStyle(fontSize = 15.sp, fontFamily = FontFamily(Font(R.font.gochihand))),
        big = TextStyle(fontSize = 40.sp, fontFamily = FontFamily(Font(R.font.gochihand)))
    )

    val colorSystem = MyColorSystem(
        contentColor = if(isDark) Color.White else Color.Black,
        barColor = listOf(
            Color(0xFFAEEA00),
            Color(0xFFD50000)
        ),
        surfaceColor = if(isDark) Color(0xFF0F0F10) else Color(0xFF9575CD),
        buttonColor = if(isDark) Color(0xFF1976D2) else Color(0xFF64B5F6)
    )



    val textSelectionColors = TextSelectionColors(handleColor = Color.Yellow, backgroundColor = Color.Cyan)

    val myElevation = MyElevation(8.dp, 16.dp)

    CompositionLocalProvider(
        LocalMyColorSystem provides colorSystem,
        LocalMyTypeSystem provides typeSystem,
        LocalMyShapeSystem provides shapeSystem,
        LocalTextSelectionColors provides textSelectionColors,
        LocalMyElevation provides myElevation,
        LocalContentAlpha provides  MyContentAlpha.high
    ){
        content()
    }



}

object NewTheme{
    val shapeSystem: MyShapeSystem
        @Composable get() = LocalMyShapeSystem.current

    val typeSystem: MyTypeSystem
        @Composable get() = LocalMyTypeSystem.current

    val colorSystem: MyColorSystem
        @Composable get() = LocalMyColorSystem.current


    val elevationSystem: MyElevation
        @Composable get() = LocalMyElevation.current



}



