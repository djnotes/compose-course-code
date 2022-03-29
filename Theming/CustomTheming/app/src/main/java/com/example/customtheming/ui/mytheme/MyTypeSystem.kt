package com.example.customtheming.ui.mytheme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.example.customtheming.R

@Immutable
data class MyTypeSystem(
    val defaultFontFamily: FontFamily,
    val tiny: TextStyle,
    val medium: TextStyle,
    val big: TextStyle
    )


val LocalMyTypeSystem = staticCompositionLocalOf {
    MyTypeSystem(
        defaultFontFamily = FontFamily.Default,
        tiny = TextStyle.Default,
        medium = TextStyle.Default,
        big = TextStyle.Default
    )
}

