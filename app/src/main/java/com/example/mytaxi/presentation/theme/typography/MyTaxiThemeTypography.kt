package com.example.mytaxi.presentation.theme.typography

import androidx.compose.ui.text.TextStyle
import com.example.mytaxi.presentation.theme.size.TextSize
import com.example.mytaxi.presentation.theme.size.TextWeight


data class MyTaxiThemeTypography(
    val bodyThin: TextStyle = TextStyle(
        fontWeight = TextWeight.thin,
        fontSize = TextSize.medium,
    ),
)