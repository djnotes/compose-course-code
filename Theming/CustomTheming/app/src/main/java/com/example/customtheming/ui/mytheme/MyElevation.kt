package com.example.customtheming.ui.mytheme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp

@Immutable
data class MyElevation(
    val normal: Dp,
    val pressed: Dp
    )


val LocalMyElevation = staticCompositionLocalOf {
    MyElevation(
        Dp.Unspecified,
        Dp.Unspecified
    )
}