package com.example.customtheming.ui.mytheme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MyColorSystem(
    val contentColor: Color,
    val barColor: List<Color>,
    val surfaceColor: Color,
    val buttonColor: Color
)

val LocalMyColorSystem = staticCompositionLocalOf{
    MyColorSystem(
        Color.Unspecified,
        listOf(),
        Color.Unspecified,
        Color.Unspecified
    )
}

