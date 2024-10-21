package com.manuelklyukvin.sign_in.presentation.screen.models

import androidx.compose.foundation.text.input.TextFieldState

data class SignInState(
    val emailState: TextFieldState = TextFieldState(),
    val isContinueButtonEnabled: Boolean = false
)
