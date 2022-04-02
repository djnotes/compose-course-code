package com.example.customtheming.ui.mytheme

import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class MyRipple: RippleTheme {
    @Composable
    override fun defaultColor(): Color {
        return Color.Blue
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleAlpha(0.2f, focusedAlpha = 0.4f, hoveredAlpha = 0.6f,
            pressedAlpha = 1f)
    }
}