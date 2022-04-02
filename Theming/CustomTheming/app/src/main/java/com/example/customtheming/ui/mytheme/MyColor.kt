package com.example.customtheming.ui.mytheme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MyColor(
    val backgroundColor: Color,
    val contentColor: Color,
    val buttonColor: Color,
    val barColor: List<Color>
    )

val LocalMyColor = staticCompositionLocalOf {
    MyColor(
        Color.Unspecified,
        Color.Unspecified,
        Color.Unspecified,
        listOf()
    )
}