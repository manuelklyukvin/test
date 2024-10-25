package com.manuelklyukvin.code.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import com.manuelklyukvin.code.presentation.R
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme

@Composable
internal fun CodeTextField(state: TextFieldState) {
    val maxLength = 1
    val valueLength = state.text.length

    LaunchedEffect(valueLength) {
        if (valueLength > maxLength) {
            state.edit {
                delete(maxLength, valueLength)
            }
        }
    }

    val textStyle = AppTheme.typography.title2

    BasicTextField(
        modifier = Modifier
            .size(AppTheme.shapes.sizeExtraLarge)
            .background(
                color = AppTheme.colorScheme.gray2,
                shape = AppTheme.shapes.radiusSmall
            )
            .padding(horizontal = AppTheme.shapes.paddingMedium),
        state = state,
        textStyle = textStyle.copy(
            color = AppTheme.colorScheme.white
        ),
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
        cursorBrush = SolidColor(AppTheme.colorScheme.white),
        decorator = { inputField ->
            Box(contentAlignment = Alignment.Center) {
                inputField()
                if (state.text.isEmpty()) {
                    Text(
                        text = stringResource(R.string.code_input_hint),
                        style = textStyle,
                        color = AppTheme.colorScheme.gray3
                    )
                }
            }
        }
    )
}