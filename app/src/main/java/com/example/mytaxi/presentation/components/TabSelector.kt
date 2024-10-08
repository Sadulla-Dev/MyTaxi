package com.example.mytaxi.presentation.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiCornerRadius
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.example.mytaxi.presentation.theme.MyTaxiTypography
import com.example.mytaxi.presentation.theme.ThemedPreview
import com.example.mytaxi.presentation.theme.color.Black

@Composable
fun TabSelector(
    modifier: Modifier = Modifier,
    selectedOption: Int = 0,
    onTabSelected: (selectedIndex: Int) -> Unit = {}
) {
    val tabs = remember { listOf("Band", "Faol") }

    BoxWithConstraints(
        modifier = modifier
            .clip(MyTaxiCornerRadius.extraLarge)
            .height(56.dp)
            .fillMaxSize()
            .background(MyTaxiColors.onBackground)
    ) {
        val segmentWidth = remember { maxWidth / tabs.size }
        val boxWidth = remember { segmentWidth - 8.dp }
        val positions = remember {
            tabs.indices.map { index ->
                segmentWidth * index + (segmentWidth - boxWidth) / 2
            }
        }
        val animatedOffsetX by animateDpAsState(
            targetValue = positions[selectedOption],
            label = ""
        )
        val verticalOffset = remember { (maxHeight - 48.dp) / 2 }

        Row(
            modifier = Modifier.fillMaxHeight(),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, text ->
                Box(
                    modifier = Modifier
                        .width(segmentWidth)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            onTabSelected(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = text,
                        color = MyTaxiColors.background,
                        style = MyTaxiTypography.titleThin
                    )
                }
            }
        }

        val selectedBackgroundColor = if (selectedOption == 0) {
            MyTaxiColors.tabSelected
        } else {
            MyTaxiColors.buttonPrimary
        }

        Box(
            modifier = Modifier
                .offset(x = animatedOffsetX, y = verticalOffset)
                .clip(MyTaxiCornerRadius.medium)
                .width(boxWidth)
                .height(48.dp)
                .background(selectedBackgroundColor)
        ) {
            Text(
                text = tabs[selectedOption],
                modifier = Modifier.align(Alignment.Center),
                color = Black,
                style = MyTaxiTypography.titleRegular
            )
        }
    }
}

@ThemedPreview
@Composable
private fun TabSelectorPreview() = MyTaxiTheme {
    Column {
        TabSelector(
            selectedOption = 0,
            onTabSelected = {}
        )
        Spacer(modifier = Modifier.height(10.dp))
        TabSelector(
            selectedOption = 1,
            onTabSelected = {}
        )
    }
}