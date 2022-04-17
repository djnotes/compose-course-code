package com.example.customtheming.ui.m3

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
fun M3Theme(
    isDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val dynamic = Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    MaterialTheme(
        colorScheme = when{
                          dynamic && isDark -> dynamicDarkColorScheme(LocalContext.current)
                          dynamic && ! isDark -> dynamicLightColorScheme(LocalContext.current)
                          isDark -> DarkColorScheme
            else -> LightColorScheme
                          },
        typography = M3Typography,
        content = content
    )
}