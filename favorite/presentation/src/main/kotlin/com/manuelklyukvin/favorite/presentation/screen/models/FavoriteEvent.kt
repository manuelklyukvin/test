package com.manuelklyukvin.favorite.presentation.screen.models

import com.manuelklyukvin.core.presentation.ui.navigation.NavigationState

sealed interface FavoriteEvent {

    data class OnVacancyClicked(
        val vacancyId: String,
        val navigationState: NavigationState
    ) : FavoriteEvent
    data class OnFavoriteButtonClicked(val vacancyId: String) : FavoriteEvent
}