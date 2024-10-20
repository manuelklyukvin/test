package com.manuelklyukvin.sign_in.presentation.screen

import androidx.lifecycle.ViewModel
import com.manuelklyukvin.core.presentation.navigation.NavigationState
import com.manuelklyukvin.core.presentation.navigation.Screen
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
    }

    private fun onContinueButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.Code(state.value.emailState.text.toString()))
    }
}