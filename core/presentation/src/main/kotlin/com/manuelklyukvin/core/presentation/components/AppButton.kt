package com.manuelklyukvin.core.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import com.manuelklyukvin.core.presentation.theme.AppTheme

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String? = null,
    isEnabled: Boolean = true,
    shape: Shape = AppTheme.shapes.radiusSmall,
    containerColor: Color = AppTheme.colorScheme.blue,
    contentColor: Color = AppTheme.colorScheme.white,
    disabledContainerColor: Color = AppTheme.colorScheme.darkBlue,
    disabledContentColor: Color = AppTheme.colorScheme.gray4,
    textStyle: TextStyle = AppTheme.typography.buttonText2,
    content: @Composable (() -> Unit)? = null
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        enabled = isEnabled,
        shape = shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        ),
    ) {
        if (text != null) {
            Text(
                text = text,
                style = textStyle,
                color = if (isEnabled) {
                    contentColor
                } else {
                    disabledContentColor
                }
            )
        } else if (content != null) {
            content()
        }
    }
}