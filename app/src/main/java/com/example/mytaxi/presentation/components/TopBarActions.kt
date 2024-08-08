package com.example.mytaxi.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytaxi.R
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.example.mytaxi.presentation.theme.ThemedPreview

@Composable
fun TopBarActions(modifier: Modifier = Modifier) {
    val selectedOption = remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        MainIconButton(
            icon = R.drawable.ic_menu,
            iconTint = MyTaxiColors.background,
            backgroundColor = MyTaxiColors.onBackground,
            onClick = {},
            modifier = Modifier.padding(horizontal = 16.dp)
        )


        TabSelector(
            modifier = Modifier.weight(1f),
            selectedOption = selectedOption.intValue,
            onTabSelected = { selectedOption.intValue = it }
        )
        MainIconButton(
            secondBackgroundColor = MyTaxiColors.buttonPrimary,
            text = "95",
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@ThemedPreview
@Composable
private fun TopBarActionsPreview() = MyTaxiTheme {
    Box(Modifier.background(MyTaxiColors.background)) {
        TopBarActions()
    }
}