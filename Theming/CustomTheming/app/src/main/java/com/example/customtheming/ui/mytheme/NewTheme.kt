package com.example.customtheming.ui.mytheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.selection.LocalTextSelectionColors
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.customtheming.R

@Composable
fun NewTheme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val colors = MyColor(
        backgroundColor = if(isDark) Color(0xFF0F1516) else Color(0xFF4FC3F7),
        contentColor = if (isDark) Color.White else Color.Black,
        buttonColor = if(isDark) Color(0xFF7B1FA2) else Color(0xFFAA00FF),
        barColor = listOf(Color(0xFFAEEA00), Color(0xFFD50000))
    )

    val shapes = MyShape(
        buttonShape = RoundedCornerShape(
            topStart = 8.dp,
            bottomEnd = 8.dp
        ),
        surfaceShape = CutCornerShape(
            topEnd = 16.dp,
            bottomStart = 16.dp
        )
    )

    val typography = MyType(
        defaultFontFamily = FontFamily(Font(R.font.gochihand)),
        small = TextStyle(fontSize = 10.sp, fontFamily = FontFamily(Font(R.font.gochihand))),
        medium = TextStyle(fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.gochihand))),
        large = TextStyle(fontSize = 40.sp, fontFamily = FontFamily(Font(R.font.gochihand))),
    )

    val myElevation = MyElevation(
        normal = 4.dp,
        pressed = 16.dp
    )

    val myTextSelectionColors = TextSelectionColors(
        handleColor = Color.Yellow,
        backgroundColor = Color.LightGray
    )

    CompositionLocalProvider(
        LocalMyColor provides colors,
        LocalMyType provides typography,
        LocalMyShape provides shapes,
        LocalMyElevation provides myElevation,
        LocalTextSelectionColors provides myTextSelectionColors,
        LocalRippleTheme provides MyRipple()
    ){
        content()
    }


}


object NewTheme{
    val myColor: MyColor
        @Composable get() = LocalMyColor.current

    val myType: MyType
        @Composable get() = LocalMyType.current

    val myShape: MyShape
     @Composable get() = LocalMyShape.current

    val myElevation: MyElevation
        @Composable get() = LocalMyElevation.current

}