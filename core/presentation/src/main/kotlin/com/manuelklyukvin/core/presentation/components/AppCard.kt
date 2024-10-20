package com.manuelklyukvin.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.manuelklyukvin.core.presentation.theme.AppTheme

@Composable
fun AppCard(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues = PaddingValues(AppTheme.shapes.paddingMedium),
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .background(
                color = AppTheme.colorScheme.grey1,
                shape = AppTheme.shapes.radiusSmall
            )
            .padding(paddingValues),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        content = content
    )
}