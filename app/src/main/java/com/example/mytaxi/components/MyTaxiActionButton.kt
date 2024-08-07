package com.example.mytaxi.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mytaxi.R
import com.example.mytaxi.ui.theme.MyTaxiColors
import com.example.mytaxi.ui.theme.MyTaxiTheme
import com.example.mytaxi.ui.theme.ThemedPreview


@Composable
fun MainIconButton(
    modifier: Modifier = Modifier,
    icon: Int? = null,
    text: String? = null,
    backgroundColor: Color = MyTaxiColors.onBackground,
    secondBackgroundColor: Color = Color.Transparent,
    iconTint: Color = MyTaxiColors.iconPrimary,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .size(56.dp)
            .clip(RoundedCornerShape(14.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(secondBackgroundColor),
            contentAlignment = Alignment.Center
        ) {
            if (icon != null) {
                Icon(
                    painter = painterResource(icon),
                    tint = iconTint,
                    modifier = Modifier.size(24.dp),
                    contentDescription = null,
                )
            }
            if (text != null) {
                Text(
                    text = text,
                    fontSize = 20.sp,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@ThemedPreview
@Composable
private fun MainIconButtonPreview() = MyTaxiTheme {
    Column(modifier = Modifier.background(MyTaxiColors.background)) {
        MainIconButton(
            secondBackgroundColor = MyTaxiColors.buttonPrimary,
            text = "95",
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
        MainIconButton(
            icon = R.drawable.ic_menu,
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
        MainIconButton(
            icon = R.drawable.ic_chevrons,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}