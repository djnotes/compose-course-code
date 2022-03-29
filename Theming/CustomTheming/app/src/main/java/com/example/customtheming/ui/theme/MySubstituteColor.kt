package com.example.customtheming.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class MySubstituteColor(
    val backgroundColor: Color,
    val contentColor: Color
)

val LocalMySubstituteColor = staticCompositionLocalOf {
    MySubstituteColor(
        backgroundColor = Color.Unspecified,
        contentColor = Color.Unspecified
    )
}