package com.example.mytaxi.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.mytaxi.R
import com.example.mytaxi.presentation.components.model.BottomSheetItem
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.example.mytaxi.presentation.theme.ThemedPreview

@Composable
fun BottomSheetContent() {
    val bottomSheetItems = remember {
        listOf(
            BottomSheetItem(
                icon = R.drawable.ic_switch,
                title = "Tarif",
                amount = "6 / 8"
            ),
            BottomSheetItem(
                icon = R.drawable.ic_order,
                title = "Buyurtmalar",
                amount = "0"
            ),
            BottomSheetItem(
                icon = R.drawable.ic_rocket,
                title = "Bordur",
                amount = ""
            ),
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 16.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MyTaxiColors.backgroundPrimary)
    ) {
        LazyColumn {
            itemsIndexed(
                items = bottomSheetItems,
                key = { _, item -> item.title },
            ) { index, item ->
                BottomSheetContentItem(item)
                if (index < bottomSheetItems.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        color = MyTaxiColors.iconPrimary,
                        thickness = 1.dp
                    )
                }
            }
        }
    }
}

@ThemedPreview
@Composable
private fun BottomSheetContentPreview() = MyTaxiTheme {
    BottomSheetContent()
}
