package com.manuelklyukvin.core.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import com.manuelklyukvin.core.presentation.theme.AppTheme

@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    icon: Painter? = null,
    hint: String? = null,
    textStyle: TextStyle = AppTheme.typography.text1,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    BasicTextField(
        modifier = modifier.background(
            color = AppTheme.colorScheme.gray2,
            shape = AppTheme.shapes.radiusSmall
        ),
        state = state,
        textStyle = textStyle.copy(color = AppTheme.colorScheme.white),
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        cursorBrush = SolidColor(AppTheme.colorScheme.white),
        decorator = { inputField ->
            Decorator(
                state = state,
                inputField = inputField,
                icon = icon,
                hint = hint,
                textStyle = textStyle
            )
        }
    )
}

@Composable
private fun Decorator(
    state: TextFieldState,
    inputField: @Composable () -> Unit,
    icon: Painter?,
    hint: String?,
    textStyle: TextStyle
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (icon != null) {
            AppIcon(
                modifier = Modifier.size(AppTheme.shapes.sizeSmall),
                painter = icon,
                tint = AppTheme.colorScheme.gray3
            )
            Spacer(modifier = Modifier.width(AppTheme.shapes.paddingSmall))
        }
        Box {
            inputField()
            if (state.text.isEmpty() && hint != null) {
                Text(
                    text = hint,
                    style = textStyle,
                    color = AppTheme.colorScheme.gray3
                )
            }
        }
    }
}