package com.manuelklyukvin.vacancy.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme

@Composable
fun VacancyTextField(
    modifier: Modifier = Modifier,
    state: TextFieldState,
    hint: String? = null,
    textStyle: TextStyle = AppTheme.typography.text1
) {
    BasicTextField(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        state = state,
        textStyle = textStyle.copy(color = AppTheme.colorScheme.white),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        cursorBrush = SolidColor(AppTheme.colorScheme.white),
        decorator = { inputField ->
            Decorator(
                state = state,
                inputField = inputField,
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
    hint: String?,
    textStyle: TextStyle
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingExtraSmall)
    ) {
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