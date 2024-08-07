package com.example.mytaxi.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytaxi.R
import com.example.mytaxi.ui.theme.MyTaxiColors
import com.example.mytaxi.ui.theme.MyTaxiTheme
import com.example.mytaxi.ui.theme.ThemedPreview
import com.example.mytaxi.ui.theme.color.Blue

@Composable
fun MapActions(
    modifier: Modifier = Modifier,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    onLocationClick: () -> Unit
) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.align(Alignment.CenterEnd)) {
            MainIconButton(
                icon = R.drawable.ic_plus,
                onClick = onPlusClick,
                backgroundColor = MyTaxiColors.onBackground.copy(alpha = 0.9f),
            )
            MainIconButton(
                icon = R.drawable.ic_minus,
                onClick = onMinusClick,
                backgroundColor = MyTaxiColors.onBackground.copy(alpha = 0.9f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            MainIconButton(
                icon = R.drawable.ic_location,
                onClick = onLocationClick,
                iconTint = Blue,
                backgroundColor = MyTaxiColors.onBackground.copy(alpha = 0.9f),
            )
        }

        MainIconButton(
            icon = R.drawable.ic_chevrons,
            onClick = {},
            secondBackgroundColor = MyTaxiColors.backgroundPrimary,
            backgroundColor = MyTaxiColors.onBackground.copy(alpha = 0.9f),
        )
    }
}

@ThemedPreview
@Composable
private fun MapActionsPreview() = MyTaxiTheme {
    Surface(color = MyTaxiColors.background) {
        MapActions(
            onPlusClick = {},
            onMinusClick = {},
            onLocationClick = {}
        )
    }
}