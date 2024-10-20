package com.manuelklyukvin.code.presentation.screen

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
class CodeViewModel @Inject constructor() : ViewModel() {

    private val _codeNumbers = List(4) {
        MutableStateFlow(TextFieldState())
    }
    val codeNumbers: List<StateFlow<TextFieldState>>
        get() = _codeNumbers.map {
            it.asStateFlow()
        }

    fun onContinueButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.Feed)
    }
}