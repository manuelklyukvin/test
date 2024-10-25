package com.manuelklyukvin.sign_in.presentation.screen.models

import com.manuelklyukvin.core.presentation.ui.navigation.NavigationState

sealed interface SignInEvent {

    data class OnContinueButtonClicked(val navigationState: NavigationState) : SignInEvent
    data object OnEmailStateUpdated : SignInEvent
}