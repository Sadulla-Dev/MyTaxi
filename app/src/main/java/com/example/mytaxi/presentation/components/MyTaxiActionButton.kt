package com.example.mytaxi.presentation.components

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mytaxi.R
import com.example.mytaxi.presentation.theme.MyTaxiColors
import com.example.mytaxi.presentation.theme.MyTaxiTheme
import com.example.mytaxi.presentation.theme.MyTaxiTypography
import com.example.mytaxi.presentation.theme.ThemedPreview
import com.example.mytaxi.presentation.utils.applyIf

@Composable
fun MainIconButton(
    modifier: Modifier = Modifier,
    icon: Int? = null,
    text: String? = null,
    backgroundColor: Color = MyTaxiColors.onBackground,
    innerBackgroundColor: Color = Color.Transparent,
    iconTint: Color = MyTaxiColors.iconPrimary,
    onClick: () -> Unit,
    onLongPress: (() -> Unit)? = null,
    onLongPressEnd: (() -> Unit)? = null,
    slideOffset: Dp = 0.dp
) {
    val animatedOffset by animateDpAsState(
        targetValue = slideOffset,
        animationSpec = tween(
            durationMillis = 500,
            easing = LinearOutSlowInEasing
        ),
        label = ""
    )

    Box(modifier = modifier
        .size(56.dp)
        .clip(RoundedCornerShape(14.dp))
        .offset(x = animatedOffset)
        .background(backgroundColor)
        .applyIf(
            condition = onLongPress == null,
            ifTrue = {
                Modifier.clickable { onClick() }
            },
            ifFalse = {
                Modifier.pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            onLongPress?.invoke()
                        },
                        onPress = {
                            awaitRelease()
                            onLongPressEnd?.invoke()
                        },
                        onTap = {
                            onClick()
                        }
                    )
                }
            }
        ),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxSize()
                .clip(RoundedCornerShape(10.dp))
                .background(innerBackgroundColor), contentAlignment = Alignment.Center
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
                    color = MyTaxiColors.textPrimary,
                    style = MyTaxiTypography.titleBold
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
            innerBackgroundColor = MyTaxiColors.buttonPrimary,
            text = "95",
            onClick = {},
            modifier = Modifier.padding(16.dp)
        )
        MainIconButton(
            icon = R.drawable.ic_menu, onClick = {}, modifier = Modifier.padding(16.dp)
        )
        MainIconButton(
            icon = R.drawable.ic_chevrons,
            onClick = {},
            modifier = Modifier.padding(16.dp),
        )
    }
}