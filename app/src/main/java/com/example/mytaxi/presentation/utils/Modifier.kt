package com.example.mytaxi.presentation.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.debugInspectorInfo

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.applyIf(
    condition: Boolean,
    ifTrue: @Composable Modifier.() -> Modifier,
    ifFalse: @Composable (Modifier.() -> Modifier)? = null,
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "applyIf"
        value = condition
    },
) {
    when {
        condition -> then(ifTrue(Modifier))
        ifFalse != null -> then(ifFalse(Modifier))
        else -> this
    }
}