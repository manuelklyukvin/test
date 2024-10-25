package com.manuelklyukvin.sign_in.presentation.screen

import androidx.lifecycle.ViewModel
import com.manuelklyukvin.core.presentation.ui.navigation.NavigationState
import com.manuelklyukvin.core.presentation.ui.navigation.Screen
import com.manuelklyukvin.sign_in.presentation.screen.models.SignInEvent
import com.manuelklyukvin.sign_in.presentation.screen.models.SignInState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState>
        get() = _state.asStateFlow()

    fun onEvent(event: SignInEvent) = when (event) {
        is SignInEvent.OnContinueButtonClicked -> onContinueButtonClicked(event.navigationState)
        SignInEvent.OnEmailStateUpdated -> onEmailStateUpdated()
    }

    private fun onContinueButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.Code(state.value.emailState.text.trim().toString()))
    }

    private fun onEmailStateUpdated() {
        _state.value = state.value.copy(isContinueButtonEnabled = state.value.emailState.text.trim().isNotEmpty())
    }
}