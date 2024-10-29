package com.manuelklyukvin.vacancy.presentation.screen.models

import com.manuelklyukvin.core.presentation.ui.navigation.NavigationState

sealed interface VacancyEvent {

    data class OnScreenInit(val vacancyId: String) : VacancyEvent
    data class OnBackButtonClicked(val navigationState: NavigationState) : VacancyEvent
    data object OnFavoriteButtonClicked : VacancyEvent
    data object OnReplyButtonClicked : VacancyEvent
    data object OnReplyScreenClosed : VacancyEvent
    data object OnAddLetterButtonClicked : VacancyEvent
    data object OnAddLetterScreenClosed : VacancyEvent
}