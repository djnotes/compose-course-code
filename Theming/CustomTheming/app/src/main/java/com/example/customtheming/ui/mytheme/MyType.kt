package com.example.customtheming.ui.mytheme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

@Immutable
data class MyType(
    val defaultFontFamily: FontFamily,
    val small: TextStyle,
    val medium: TextStyle,
    val large: TextStyle
    )


val LocalMyType = staticCompositionLocalOf {
    MyType(
        FontFamily.Default,
        TextStyle.Default,
        TextStyle.Default,
        TextStyle.Default
    )
}