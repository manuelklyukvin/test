package com.manuelklyukvin.sign_in.presentation.screen

import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import com.manuelklyukvin.core.presentation.navigation.NavigationState
import com.manuelklyukvin.core.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _emailState = MutableStateFlow(TextFieldState())
    val emailState: StateFlow<TextFieldState>
        get() = _emailState.asStateFlow()

    fun onContinueButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.Code(emailState.value.text.toString()))
    }
}