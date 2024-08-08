package com.example.mytaxi.presentation.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.mytaxi.R
import com.example.mytaxi.presentation.theme.size.TextSize
import com.example.mytaxi.presentation.theme.size.TextWeight

data class MyTaxiThemeTypography(
    val bodyBold: TextStyle = TextStyle(
        fontWeight = TextWeight.regular,
        fontSize = TextSize.large,
        fontFamily = FontFamily(Font(R.font.loto)),
    ),

    val titleThin: TextStyle = TextStyle(
        fontWeight = TextWeight.thin,
        fontSize = TextSize.large,
        fontFamily = FontFamily(Font(R.font.loto)),
    ),

    val titleRegular: TextStyle = TextStyle(
        fontWeight = TextWeight.regular,
        fontSize = TextSize.large,
        fontFamily = FontFamily(Font(R.font.loto)),
    ),

    val titleBold: TextStyle = TextStyle(
        fontWeight = TextWeight.bold,
        fontSize = TextSize.extraLarge,
        fontFamily = FontFamily(Font(R.font.loto)),
    ),
)