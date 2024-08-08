package com.example.mytaxi.presentation.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.mytaxi.presentation.theme.MyTaxiColors

@Composable
fun TabSelector(
    onSelectionChanged: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableIntStateOf(0) }

    Row(
        modifier = modifier
            .wrapContentSize()
            .height(56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MyTaxiColors.onBackground)
            .padding(4.dp)
    ) {
        TabSelectorItem(
            text = "Band",
            selected = selectedIndex == 0,
            onClick = {
                selectedIndex = 0
                onSelectionChanged(0)
            },
            modifier = Modifier.weight(1f),
            selectedColor = MyTaxiColors.tabSelected
        )
        TabSelectorItem(
            text = "Faol",
            selected = selectedIndex == 1,
            onClick = {
                selectedIndex = 1
                onSelectionChanged(1)
            },
            modifier = Modifier.weight(1f),
            selectedColor = MyTaxiColors.buttonPrimary
        )
    }
}

@Composable
private fun TabSelectorItem(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedColor: Color
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (selected) selectedColor else MyTaxiColors.onBackground,
        label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (selected)
            MyTaxiColors.onBackground
        else MyTaxiColors.background,
        label = ""
    )

    Box(
        modifier = modifier
            .fillMaxHeight()
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            color = textColor,
            style = MaterialTheme.typography.labelLarge
        )
    }
}
