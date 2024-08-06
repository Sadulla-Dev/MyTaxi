package com.example.mytaxi.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mytaxi.R
import com.example.mytaxi.ui.theme.MyTaxiColors
import com.example.mytaxi.ui.theme.MyTaxiTheme
import com.example.mytaxi.ui.theme.ThemedPreview
import com.example.mytaxi.ui.theme.color.Black

@Composable
fun TopBarActions(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        TopBarMenuAction()
        TabSelector(
            onSelectionChanged = {},
            modifier = Modifier.weight(1f)
        )
        MainIconButton(
            secondBackgroundColor = MyTaxiColors.buttonPrimary,
            text = "95",
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun TopBarMenuAction(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(MyTaxiColors.background)
            .clickable { }
    ) {
        Icon(
            modifier = Modifier
                .padding(16.dp)
                .size(24.dp),
            tint = MyTaxiColors.onBackground,
            painter = painterResource(R.drawable.ic_menu),
            contentDescription = null
        )
    }
}

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
            .background(MyTaxiColors.secondary)
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
            selectedColor = Color.Red
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
        targetValue = if (selected) selectedColor else MyTaxiColors.background,
        label = ""
    )
    val textColor by animateColorAsState(
        targetValue = if (selected) {
            if (selectedColor == MyTaxiColors.tabSelected) MyTaxiColors.onBackground else Black
        } else MaterialTheme.colorScheme.onSurface,
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


@ThemedPreview
@Composable
private fun TopBarActionsPreview() = MyTaxiTheme {
    Box(Modifier.background(MaterialTheme.colorScheme.onBackground)) {
        TopBarActions()
    }
}