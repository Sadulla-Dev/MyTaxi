package com.example.mytaxi.presentation.theme.color

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@Stable
class MyTaxiThemeColors(
    background: Color,
    onBackground: Color,
    backgroundPrimary: Color,
    secondary: Color,
    buttonPrimary: Color,
    iconPrimary: Color,
    tabSelected: Color,
) {
    var background by mutableStateOf(background)
        private set
    var onBackground by mutableStateOf(onBackground)
        private set

    var backgroundPrimary by mutableStateOf(backgroundPrimary)
        private set

    var secondary by mutableStateOf(secondary)
        private set

    var buttonPrimary by mutableStateOf(buttonPrimary)
        private set

    var iconPrimary by mutableStateOf(iconPrimary)
        private set

    var tabSelected by mutableStateOf(tabSelected)
        private set

    fun copy(
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        secondary: Color = this.secondary,
        buttonPrimary: Color = this.buttonPrimary,
        backgroundPrimary: Color = this.backgroundPrimary,
        iconPrimary: Color = this.iconPrimary,
        tabSelected: Color = this.tabSelected,
    ) = MyTaxiThemeColors(
        background = background,
        onBackground = onBackground,
        secondary = secondary,
        buttonPrimary = buttonPrimary,
        backgroundPrimary = backgroundPrimary,
        iconPrimary = iconPrimary,
        tabSelected = tabSelected,
    )

    fun updateColorsFrom(other: MyTaxiThemeColors) {
        background = other.background
        onBackground = other.onBackground
        secondary = other.secondary
        buttonPrimary = other.buttonPrimary
        buttonPrimary = other.buttonPrimary
        iconPrimary = other.iconPrimary
        tabSelected = other.tabSelected
    }
}