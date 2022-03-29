package com.example.customtheming.ui.mytheme

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector2D
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MyColorSystem(
    val bg1: Color,
    val bg2: Color,
    val contentColor: Color,
    val barColor: List<Color>
)

val LocalMyColorSystem = staticCompositionLocalOf{
    MyColorSystem(
        Color.Unspecified,
        Color.Unspecified,
    Color.Unspecified,
        listOf()
    )
}

