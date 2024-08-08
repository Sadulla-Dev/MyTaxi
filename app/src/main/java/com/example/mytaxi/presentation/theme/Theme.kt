package com.example.mytaxi.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import com.example.mytaxi.presentation.theme.color.MyTaxiThemeColors
import com.example.mytaxi.presentation.theme.color.darkColors
import com.example.mytaxi.presentation.theme.color.lightColors
import com.example.mytaxi.presentation.theme.typography.MyTaxiThemeTypography

val MyTaxiColors
    @Composable
    get() = MyTaxiTheme.colors

val MyTaxiTypography
    @Composable
    get() = MyTaxiTheme.typography

private object MyTaxiTheme {
    val colors: MyTaxiThemeColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: MyTaxiThemeTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}

val LocalColors = compositionLocalOf { lightColors() }

val LocalTypography = compositionLocalOf { MyTaxiThemeTypography() }

@Composable
fun MyTaxiTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    typography: MyTaxiThemeTypography = MyTaxiTheme.typography,
    content: @Composable () -> Unit
) {
    val currentColor = if (darkTheme) darkColors() else lightColors()

    val rememberedColors = remember {
        currentColor.copy()
    }.apply { updateColorsFrom(currentColor) }

    CompositionLocalProvider(
        LocalColors provides rememberedColors,
        LocalTypography provides typography,
    ) {
        MaterialTheme {
            content()
        }
    }
}
