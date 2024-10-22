package com.manuelklyukvin.search.presentation.screen.models

import com.manuelklyukvin.core.presentation.navigation.NavigationState

sealed interface SearchEvent {

    data class OnVacancyClicked(
        val vacancyId: String,
        val navigationState: NavigationState
    ) : SearchEvent
    data object OnShowMoreVacanciesButtonClicked : SearchEvent
}