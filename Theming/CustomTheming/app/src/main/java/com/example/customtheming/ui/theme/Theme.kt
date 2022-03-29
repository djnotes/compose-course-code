package com.example.customtheming.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
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

    val mySubstituteColorSystem = MySubstituteColor(
        contentColor = if (isSystemInDarkTheme()) Color(0xFF000000) else Color(0xFF512DA8),
        backgroundColor = if (isSystemInDarkTheme()) Color(0xFFD32F2F) else Color(0xFFAED581)
        )

    val mySubstituteType = MySubstituteType(
        defaultFontFamily = FontFamily(Font(com.example.customtheming.R.font.gochihand)),
        header = TextStyle(fontSize = 30.sp),
        body = TextStyle(fontSize = 20.sp)
    )


    CompositionLocalProvider(
        LocalMyButtonColors provides myButtonColors,
        LocalMySubstituteColorSystem provides mySubstituteColorSystem,
        LocalMySubstituteTypeSystem provides mySubstituteType
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

    val mySubstituteTypeSystem: MySubstituteType
        @Composable get() = LocalMySubstituteTypeSystem.current

    val mySubstituteColorSystem: MySubstituteColor
        @Composable get() = LocalMySubstituteColorSystem.current
}

@Immutable
data class MySubstituteType(
    val defaultFontFamily: FontFamily,
    val header: TextStyle,
    val body: TextStyle
)

val LocalMySubstituteTypeSystem = staticCompositionLocalOf{
    MySubstituteType(defaultFontFamily = FontFamily.Default,
    header = TextStyle.Default,
    body = TextStyle.Default)
}

@Immutable
data class MySubstituteColor(
    val contentColor: Color,
    val backgroundColor: Color
)

val LocalMySubstituteColorSystem = staticCompositionLocalOf {
    MySubstituteColor(
        contentColor = Color.Unspecified,
        backgroundColor = Color.Unspecified
    )
}

