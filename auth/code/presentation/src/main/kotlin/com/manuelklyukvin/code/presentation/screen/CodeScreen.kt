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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manuelklyukvin.code.presentation.R
import com.manuelklyukvin.code.presentation.utils.CodeTextField
import com.manuelklyukvin.core.presentation.components.AppButton
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.core.presentation.theme.LocalNavigationState

@Composable
fun CodeScreen(
    viewModel: CodeViewModel = hiltViewModel(),
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
        CodeInput(viewModel)
    }
}

@Composable
private fun CodeInput(viewModel: CodeViewModel) {
    val codeNumbers = viewModel.codeNumbers.map {
        it.collectAsState().value
    }

    val navigationState = LocalNavigationState.current

    val isContinueButtonEnabled = codeNumbers.all {
        it.text.isNotEmpty()
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingExtraSmall)
    ) {
        for (number in codeNumbers) {
            CodeTextField(number)
        }
    }

    AppButton(
        modifier = Modifier
            .fillMaxWidth()
            .height(AppTheme.shapes.sizeExtraLarge),
        text = stringResource(R.string.code_button),
        isEnabled = isContinueButtonEnabled,
        textStyle = AppTheme.typography.buttonText1,
        onClick = {
            viewModel.onContinueButtonClicked(navigationState)
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
                email = "example@gmail.com",
                viewModel = CodeViewModel()
            )
        }
    }
}