package com.example.customtheming.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.material.icons.materialIcon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

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

    val mySubstituteColor = MySubstituteColor(
        backgroundColor = if(darkTheme) Color.DarkGray else Color.White,
        contentColor = if(darkTheme) Color.Yellow else Color.Black
    )

    val mySubstituteType = MySubstituteType(
        defaultFontFamily = FontFamily(Font(com.example.customtheming.R.font.gochihand)),
        header = TextStyle(fontFamily = FontFamily(Font(com.example.customtheming.R.font.gochihand)), fontSize = 40.sp),
        body = TextStyle(fontFamily = FontFamily(Font(com.example.customtheming.R.font.gochihand)),
            fontSize = 20.sp)
    )

    CompositionLocalProvider(
        LocalMyButtonColors provides myButtonColors,
        LocalMySubstituteType provides mySubstituteType,
        LocalMySubstituteColor provides mySubstituteColor
    ){
        MaterialTheme(
//            colors = colors,
//            typography = Typography,
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

    val mySubstituteType: MySubstituteType
        @Composable get() = LocalMySubstituteType.current

    val mySubstituteColor: MySubstituteColor
        @Composable get() = LocalMySubstituteColor.current
}