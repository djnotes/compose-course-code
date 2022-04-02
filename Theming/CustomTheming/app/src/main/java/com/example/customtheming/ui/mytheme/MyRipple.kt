package com.example.customtheming.ui.mytheme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

class MyRipple: RippleTheme {


    @Composable
    override fun defaultColor(): Color {
        return RippleTheme.defaultRippleColor(Color.Blue, !isSystemInDarkTheme())
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        return RippleAlpha(0.2f, focusedAlpha = 0.4f, 0.7f, 1f)

    }

}