package com.example.customtheming.ui.mytheme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape

@Immutable
data class MyShapeSystem(
    val round: RoundedCornerShape,
    val cut: CutCornerShape
    )

val LocalMyShapeSystem = staticCompositionLocalOf {
    MyShapeSystem(
        round = RoundedCornerShape(ZeroCornerSize),
        cut = CutCornerShape(ZeroCornerSize)
    )
}

