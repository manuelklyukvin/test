package com.manuelklyukvin.sign_in.presentation.screen.models

import com.manuelklyukvin.core.presentation.navigation.NavigationState

sealed interface SignInEvent {

    data class OnContinueButtonClicked(val navigationState: NavigationState) : SignInEvent
}