package com.example.mytaxi.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.example.mytaxi.presentation.theme.ThemedPreview

@Composable
fun MyTaxiDivider(
    isVisible: Boolean,
) {
    if (isVisible) {
        HorizontalDivider(
            modifier = Modifier.padding(horizontal = 16.dp),
            color = MyTaxiColors.divider,
            thickness = 1.dp
        )
    }
}

@ThemedPreview
@Composable
private fun MyTaxiDividerPreview() = MyTaxiTheme {
    MyTaxiDivider(true)
}