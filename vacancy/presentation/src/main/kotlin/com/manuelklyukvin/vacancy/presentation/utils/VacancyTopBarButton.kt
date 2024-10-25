package com.manuelklyukvin.vacancy.presentation.utils

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.manuelklyukvin.core.presentation.ui.components.AppIcon
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme

@Composable
internal fun VacancyTopBarButton(
    icon: Painter,
    tint: Color = AppTheme.colorScheme.white,
    onClick: () -> Unit = {  }
) {
    IconButton(
        modifier = Modifier.size(AppTheme.shapes.sizeSmall),
        onClick = onClick
    ) {
        AppIcon(
            modifier = Modifier.fillMaxSize(),
            painter = icon,
            tint = tint
        )
    }
}