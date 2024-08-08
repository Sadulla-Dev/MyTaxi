package com.example.mytaxi.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mytaxi.R
import com.example.mytaxi.presentation.components.model.BottomSheetItem
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.example.mytaxi.presentation.theme.MyTaxiTypography
import com.example.mytaxi.presentation.theme.ThemedPreview

@Composable
fun BottomSheetContentItem(bottomSheetItem: BottomSheetItem) {
    Row(
        modifier = Modifier.padding(17.dp)
    ) {
        Icon(
            painter = painterResource(id = bottomSheetItem.icon),
            contentDescription = null,
            tint = MyTaxiColors.iconPrimary,
            modifier = Modifier.padding(end = 8.dp)
        )

        Text(
            text = bottomSheetItem.title,
            color = MyTaxiColors.background,
            style = MyTaxiTypography.bodyBold
        )

        Spacer(modifier = Modifier.weight(1f))

        Text(
            text = bottomSheetItem.amount,
            color = MyTaxiColors.textSecondary,
            style = MyTaxiTypography.bodyBold
        )

        Icon(
            painter = painterResource(id = R.drawable.ic_next),
            contentDescription = null,
            tint = MyTaxiColors.iconPrimary,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
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