package com.example.mytaxi.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mytaxi.R
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.example.mytaxi.presentation.theme.ThemedPreview
import kotlinx.coroutines.delay

@Composable
fun MapActions(
    modifier: Modifier = Modifier,
    onPlusClick: () -> Unit,
    onMinusClick: () -> Unit,
    onLocationClick: () -> Unit,
    slideOffset: Dp = 0.dp
) {
    var isZoomingIn by remember { mutableStateOf(false) }
    var isZoomingOut by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Column(modifier = Modifier.align(Alignment.CenterEnd)) {
            MainIconButton(
                icon = R.drawable.ic_plus,
                slideOffset = slideOffset,
                onClick = onPlusClick,
                onLongPress = { isZoomingIn = true },
                onLongPressEnd = { isZoomingIn = false },
                backgroundColor = MyTaxiColors.onBackground.copy(alpha = 0.9f),
            )
            MainIconButton(
                icon = R.drawable.ic_minus,
                onClick = onMinusClick,
                slideOffset = slideOffset,
                onLongPress = { isZoomingOut = true },
                onLongPressEnd = { isZoomingOut = false },
                backgroundColor = MyTaxiColors.onBackground.copy(alpha = 0.9f),
                modifier = Modifier.padding(vertical = 16.dp)
            )
            MainIconButton(
                icon = R.drawable.ic_location,
                onClick = onLocationClick,
                slideOffset = slideOffset,
                iconTint = MyTaxiColors.iconAccent,
                backgroundColor = MyTaxiColors.onBackground.copy(alpha = 0.9f),
            )
        }

        MainIconButton(
            icon = R.drawable.ic_chevrons,
            onClick = {},
            slideOffset = -slideOffset,
            innerBackgroundColor = MyTaxiColors.backgroundPrimary,
            backgroundColor = MyTaxiColors.onBackground.copy(alpha = 0.9f),
        )
    }

    LaunchedEffect(isZoomingIn) {
        while (isZoomingIn) {
            onPlusClick()
            delay(100)
        }
    }

    LaunchedEffect(isZoomingOut) {
        while (isZoomingOut) {
            onMinusClick()
            delay(100)
        }
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