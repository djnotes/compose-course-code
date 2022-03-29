package com.example.customtheming.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily

@Immutable
data class MySubstituteType(
    val defaultFontFamily: FontFamily,
    val header: TextStyle,
    val body: TextStyle
    )

val LocalMySubstituteType = staticCompositionLocalOf {
    MySubstituteType(
        defaultFontFamily = FontFamily.Default,
        header = TextStyle.Default,
        body = TextStyle.Default
    )
}

