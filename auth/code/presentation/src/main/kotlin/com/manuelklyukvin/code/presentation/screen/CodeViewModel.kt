package com.manuelklyukvin.code.presentation.screen

import androidx.lifecycle.ViewModel
import com.manuelklyukvin.code.presentation.screen.models.CodeEvent
import com.manuelklyukvin.code.presentation.screen.models.CodeState
import com.manuelklyukvin.core.presentation.navigation.NavigationState
import com.manuelklyukvin.core.presentation.navigation.Screen
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class CodeViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(CodeState())
    val state: StateFlow<CodeState>
        get() = _state.asStateFlow()

    fun onEvent(event: CodeEvent) = when (event) {
        is CodeEvent.OnContinueButtonClicked -> onContinueButtonClicked(event.navigationState)
        CodeEvent.OnCodeStateUpdated -> onCodeStateUpdated()
    }

    private fun onContinueButtonClicked(navigationState: NavigationState) {
        navigationState.navigate(Screen.SearchBlock)
    }

    private fun onCodeStateUpdated() {
        _state.value = state.value.copy(
            isContinueButtonEnabled = state.value.codeNumbers.all {
                it.text.isNotEmpty()
            }
        )
    }
}