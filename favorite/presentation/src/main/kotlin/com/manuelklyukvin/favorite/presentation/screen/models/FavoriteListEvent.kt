package com.manuelklyukvin.favorite.presentation.screen.models

import com.manuelklyukvin.core.presentation.navigation.NavigationState

sealed interface FavoriteListEvent {

    data class OnVacancyClicked(
        val vacancyId: String,
        val navigationState: NavigationState
    ) : FavoriteListEvent
}