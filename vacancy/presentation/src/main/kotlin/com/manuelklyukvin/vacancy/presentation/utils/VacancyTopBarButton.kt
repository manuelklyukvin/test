package com.manuelklyukvin.vacancy.presentation.utils

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.manuelklyukvin.core.presentation.ui.components.AppIcon
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme
import com.manuelklyukvin.core.presentation.ui.utils.noIndicationClickable

@Composable
internal fun VacancyTopBarButton(
    icon: Painter,
    tint: Color = AppTheme.colorScheme.white,
    onClick: () -> Unit = {  }
) {
    AppIcon(
        modifier = Modifier
            .size(AppTheme.shapes.sizeSmall)
            .noIndicationClickable {
                onClick()
            },
        painter = icon,
        tint = tint
    )
}