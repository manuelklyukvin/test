package com.manuelklyukvin.code.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelklyukvin.code.presentation.R
import com.manuelklyukvin.code.presentation.screen.models.CodeEvent
import com.manuelklyukvin.code.presentation.screen.models.CodeState
import com.manuelklyukvin.code.presentation.utils.CodeTextField
import com.manuelklyukvin.core.presentation.components.AppButton
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.core.presentation.theme.LocalNavigationState

@Composable
fun CodeScreen(
    state: CodeState,
    onEvent: (CodeEvent) -> Unit,
    email: String
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 130.dp)
            .padding(horizontal = AppTheme.shapes.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingMedium)
    ) {
        Text(
            text = stringResource(R.string.code_title, email),
            style = AppTheme.typography.title2,
            color = AppTheme.colorScheme.white
        )
        Text(
            text = stringResource(R.string.code_subtitle),
            style = AppTheme.typography.title3,
            color = AppTheme.colorScheme.white
        )
        CodeInput(state, onEvent)
    }
}

@Composable
private fun CodeInput(state: CodeState, onEvent: (CodeEvent) -> Unit) {
    val codeNumbersText by remember {
        derivedStateOf {
            state.codeNumbers.map { codeNumber ->
                codeNumber.text
            }
        }
    }

    val navigationState = LocalNavigationState.current

    LaunchedEffect(codeNumbersText) {
        onEvent(CodeEvent.OnCodeStateUpdated)
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingExtraSmall)
    ) {
        for (number in state.codeNumbers) {
            CodeTextField(number)
        }
    }

    AppButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(AppTheme.shapes.sizeExtraLarge),
        text = stringResource(R.string.code_button),
        isEnabled = state.isContinueButtonEnabled,
        textStyle = AppTheme.typography.buttonText1,
        onClick = {
            onEvent(CodeEvent.OnContinueButtonClicked(navigationState))
        }
    )
}

@Preview
@Composable
private fun CodeScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.black
        ) {
            CodeScreen(
                state = CodeState(),
                onEvent = {  },
                email = "example@gmail.com"
            )
        }
    }
}