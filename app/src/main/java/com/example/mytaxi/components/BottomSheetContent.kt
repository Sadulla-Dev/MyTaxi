package com.example.mytaxi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mytaxi.R
import com.example.mytaxi.ui.theme.MyTaxiColors
import com.example.mytaxi.ui.theme.MyTaxiTheme
import com.example.mytaxi.ui.theme.ThemedPreview

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

@Composable
fun BottomSheetContentItem(bottomSheetItem: BottomSheetItem) {
    Row(
        modifier = Modifier.padding(16.dp)
    ) {
        Icon(
            painter = painterResource(id = bottomSheetItem.icon),
            contentDescription = null,
            tint = MyTaxiColors.iconPrimary,
            modifier = Modifier.padding(end = 8.dp)
        )

        Text(
            text = bottomSheetItem.title,
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = bottomSheetItem.amount,
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = null,
            tint = MyTaxiColors.iconPrimary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

data class BottomSheetItem(
    val icon: Int,
    val title: String,
    val amount: String,
)

@ThemedPreview
@Composable
private fun BottomSheetContentPreview() = MyTaxiTheme {
    BottomSheetContent()
}

@ThemedPreview
@Composable
private fun BottomSheetContentItemPreview() = MyTaxiTheme {
    BottomSheetContentItem(
        bottomSheetItem = BottomSheetItem(
            icon = R.drawable.ic_switch,
            title = "Title",
            amount = "12"
        )
    )
}