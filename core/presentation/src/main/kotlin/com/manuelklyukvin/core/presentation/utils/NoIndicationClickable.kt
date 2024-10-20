package com.manuelklyukvin.core.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed

fun Modifier.noIndicationClickable(onClick: () -> Unit) = composed {
    clickable(
        onClick = onClick,
        interactionSource = remember {
            MutableInteractionSource()
        },
        indication = null
    )
}