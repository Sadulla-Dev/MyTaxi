package com.example.mytaxi.ui.theme.typography

import androidx.compose.ui.text.TextStyle
import com.example.mytaxi.ui.theme.size.TextSize
import com.example.mytaxi.ui.theme.size.TextWeight


data class MyTaxiThemeTypography(
    val bodyThin: TextStyle = TextStyle(
        fontWeight = TextWeight.thin,
        fontSize = TextSize.medium,
    ),
)