package com.example.mytaxi.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiCornerRadius


@Composable
fun SheetDragHandle() {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
                .background(Color.Transparent)
        ) {
            Box(
                modifier = Modifier
                    .width(32.dp)
                    .height(10.dp)
                    .clip(MyTaxiCornerRadius.small)
                    .background(MyTaxiColors.secondary)
                    .align(Alignment.Center)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .background(Color.Transparent)
        )
    }
}