package com.manuelklyukvin.sign_in.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manuelklyukvin.core.presentation.components.AppButton
import com.manuelklyukvin.core.presentation.components.AppCard
import com.manuelklyukvin.core.presentation.components.AppTextField
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.core.presentation.theme.LocalNavigationState
import com.manuelklyukvin.sign_in.presentation.R

@Composable
fun SignInScreen(viewModel: SignInViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = AppTheme.shapes.paddingMedium,
                vertical = AppTheme.shapes.paddingExtraLarge
            )
    ) {
        Text(
            text = stringResource(R.string.sign_in_title),
            style = AppTheme.typography.title2,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.height(144.dp))
        WorkerForm(viewModel)
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
        EmployerForm()
    }
}

@Composable
private fun WorkerForm(viewModel: SignInViewModel) {
    val emailState by viewModel.emailState.collectAsState()
    val isContinueButtonEnabled = emailState.text.isNotEmpty()

    val navigationState = LocalNavigationState.current

    AppCard(
        modifier = Modifier.fillMaxWidth(),
        paddingValues = PaddingValues(
            horizontal = AppTheme.shapes.paddingMedium,
            vertical = AppTheme.shapes.paddingLarge
        ),
        verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingMedium)
    ) {
        Text(
            text = stringResource(R.string.sign_in_worker_form_title),
            style = AppTheme.typography.title3,
            color = AppTheme.colorScheme.white
        )
        AppTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.shapes.sizeLarge),
            state = emailState,
            keyboardType = KeyboardType.Email
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppButton(
                modifier = Modifier
                    .weight(1f)
                    .height(AppTheme.shapes.sizeLarge),
                text = stringResource(R.string.sign_in_worker_form_continue_button),
                isEnabled = isContinueButtonEnabled,
                onClick = {
                    viewModel.onContinueButtonClicked(navigationState)
                }
            )
            Spacer(modifier = Modifier.width(AppTheme.shapes.paddingLarge))
            Text(
                text = stringResource(R.string.sign_in_worker_form_password_button),
                style = AppTheme.typography.buttonText2,
                color = AppTheme.colorScheme.blue
            )
        }
    }
}

@Composable
private fun EmployerForm() {
    AppCard(
        modifier = Modifier.fillMaxWidth(),
        paddingValues = PaddingValues(
            horizontal = AppTheme.shapes.paddingMedium,
            vertical = AppTheme.shapes.paddingLarge
        )
    ) {
        Text(
            text = stringResource(R.string.sign_in_employer_form_title),
            style = AppTheme.typography.title3,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingExtraSmall))
        Text(
            text = stringResource(R.string.sign_in_employer_form_subtitle),
            style = AppTheme.typography.buttonText2,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.shapes.sizeMedium),
            text = stringResource(R.string.sign_in_employer_form_button),
            shape = AppTheme.shapes.radiusLarge,
            containerColor = AppTheme.colorScheme.green,
            disabledContainerColor = AppTheme.colorScheme.darkGreen,
            onClick = {  }
        )
    }
}

// Использую MVVM, а не MVI, поэтому превью по-дурацки сделан
@Preview
@Composable
private fun SignInScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.black
        ) {
            SignInScreen(viewModel = SignInViewModel())
        }
    }
}