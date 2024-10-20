package com.manuelklyukvin.code.presentation.screen.models

import androidx.compose.foundation.text.input.TextFieldState

data class CodeState(
    val codeNumbers: List<TextFieldState> = List(4) {
        TextFieldState()
    }
)
