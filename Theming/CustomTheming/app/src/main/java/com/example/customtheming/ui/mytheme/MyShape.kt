package com.example.customtheming.ui.mytheme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape

@Immutable
data class MyShape(
    val buttonShape: Shape,
    val surfaceShape: Shape
    )

val LocalMyShape = staticCompositionLocalOf {
    MyShape(
        RectangleShape,
        RectangleShape
    )
}