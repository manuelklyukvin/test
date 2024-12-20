package com.manuelklyukvin.code.presentation.screen.models

import com.manuelklyukvin.core.presentation.ui.navigation.NavigationState

sealed interface CodeEvent {

    data class OnContinueButtonClicked(val navigationState: NavigationState) : CodeEvent
    data object OnCodeStateUpdated : CodeEvent
}