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

@Composable
fun NewTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val shapeSystem = MyShapeSystem(
        round = RoundedCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
        cut = CutCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
    )

    val typeSystem = MyTypeSystem(
        defaultFontFamily = FontFamily(Font(R.font.gochihand)),
        tiny = TextStyle(fontSize = 10.sp),
        medium = TextStyle(fontSize = 15.sp),
        big = TextStyle(fontSize = 30.sp)
    )

    val colorSystem = MyColorSystem(
        bg1 = if(isDark) Color.LightGray else Color.Blue,
        bg2 = if (isDark) Color.Green else Color.Red,
        contentColor = if(isDark) Color.Black else Color.White,
        barColor = listOf(
            Color.Red,
            Color.Green
        )
    )



    val textSelectionColors = TextSelectionColors(handleColor = Color.Yellow, backgroundColor = Color.Cyan)

    val myElevation = MyElevation(4.dp, 16.dp)

    CompositionLocalProvider(
        LocalMyColorSystem provides colorSystem,
        LocalMyTypeSystem provides typeSystem,
        LocalMyShapeSystem provides shapeSystem,
        LocalContentAlpha provides ContentAlpha.medium,
        LocalTextSelectionColors provides textSelectionColors,
        LocalMyElevation provides myElevation
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



