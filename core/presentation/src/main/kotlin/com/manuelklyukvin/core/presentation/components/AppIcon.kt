package com.manuelklyukvin.core.presentation.components

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.manuelklyukvin.core.presentation.theme.AppTheme

@Composable
fun AppIcon(
    modifier: Modifier = Modifier,
    painter: Painter,
    tint: Color = AppTheme.colorScheme.white
) {
    Icon(
        modifier = modifier,
        painter = painter,
        tint = tint,
        contentDescription = null
    )
}