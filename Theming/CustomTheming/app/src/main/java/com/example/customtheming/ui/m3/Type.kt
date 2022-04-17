package com.example.customtheming.ui.m3

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.customtheming.R

val M3Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.capriola)
        ),
        fontWeight = FontWeight.ExtraBold
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.capriola)
        ),
        fontWeight = FontWeight.Bold
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(
            Font(R.font.capriola)
        ),
        fontWeight = FontWeight.Normal
    ),

    )