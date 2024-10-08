package com.example.mytaxi.presentation.theme.corners

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class MyTaxiThemeCornerRadius(
    val small: Shape = RoundedCornerShape(6.dp),
    val medium: Shape = RoundedCornerShape(10.dp),
    val large: Shape = RoundedCornerShape(12.dp),
    val extraLarge: Shape = RoundedCornerShape(14.dp),
    val huge: Dp = 18.dp
)
