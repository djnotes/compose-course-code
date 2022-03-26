package com.example.customtheming.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColors(
    primary = Purple500,
    primaryVariant = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

//TODO 1: Add extension theme property
//TODO 2: Wrap MaterialTheme with extended theme
//TODO 3: Replace some design systems in MaterialTheme e.g. Type, Color, Shape, etc.


val Colors.fabColor: Color
  get() = if(isLight) Color(0xFFF06292) else Color(0xFFC2185B)

@Composable
fun CustomThemingTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val myButtonColors = MyButtonColors(
        backgroundColor = if(isSystemInDarkTheme()) Color(0xFF7B1FA2) else Color(0xFFD32F2F),
        contentColor = if(isSystemInDarkTheme()) Color(0xFFFEF9F9) else Color(0xFF000003)
    )

    CompositionLocalProvider(
        LocalMyButtonColors provides myButtonColors
    ){
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }


}


@Immutable
data class MyButtonColors(val backgroundColor: Color,
val contentColor: Color
)

val LocalMyButtonColors = staticCompositionLocalOf{
    MyButtonColors(Color.Unspecified, Color.Unspecified)
}

object CustomThemingTheme{
    val myButtonColors: MyButtonColors
      @Composable get() = LocalMyButtonColors.current
}