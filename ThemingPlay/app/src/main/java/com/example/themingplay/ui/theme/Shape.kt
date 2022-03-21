package com.example.themingplay.ui.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = CutCornerShape(topStart = 8.dp, bottomEnd = 8.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(topEnd = 32.dp, bottomEnd = 32.dp)
)