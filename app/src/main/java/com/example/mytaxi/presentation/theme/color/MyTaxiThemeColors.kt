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
    textPrimary: Color,
    textSecondary: Color,
    iconPrimary: Color,
    iconAccent: Color,
    tabSelected: Color,
    divider: Color,
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

    var textPrimary by mutableStateOf(textPrimary)
        private set

    var iconAccent by mutableStateOf(iconAccent)
        private set

    var textSecondary by mutableStateOf(textSecondary)
        private set

    var divider by mutableStateOf(divider)
        private set

    fun copy(
        background: Color = this.background,
        onBackground: Color = this.onBackground,
        secondary: Color = this.secondary,
        buttonPrimary: Color = this.buttonPrimary,
        backgroundPrimary: Color = this.backgroundPrimary,
        iconPrimary: Color = this.iconPrimary,
        tabSelected: Color = this.tabSelected,
        textPrimary: Color = this.textPrimary,
        iconAccent: Color = this.iconAccent,
        textSecondary: Color = this.textSecondary,
        divider: Color = this.divider,
    ) = MyTaxiThemeColors(
        background = background,
        onBackground = onBackground,
        secondary = secondary,
        buttonPrimary = buttonPrimary,
        backgroundPrimary = backgroundPrimary,
        iconPrimary = iconPrimary,
        tabSelected = tabSelected,
        textPrimary = textPrimary,
        iconAccent = iconAccent,
        textSecondary = textSecondary,
        divider = divider,
    )

    fun updateColorsFrom(other: MyTaxiThemeColors) {
        background = other.background
        onBackground = other.onBackground
        secondary = other.secondary
        buttonPrimary = other.buttonPrimary
        buttonPrimary = other.buttonPrimary
        iconPrimary = other.iconPrimary
        tabSelected = other.tabSelected
        textPrimary = other.textPrimary
        iconAccent = other.iconAccent
        textSecondary = other.textSecondary
        divider = other.divider
    }
}