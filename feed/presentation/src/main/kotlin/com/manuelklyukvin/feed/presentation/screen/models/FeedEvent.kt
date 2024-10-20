package com.manuelklyukvin.feed.presentation.screen.models

import com.manuelklyukvin.core.presentation.navigation.NavigationState

sealed interface FeedEvent {

    data class OnVacancyClicked(
        val vacancyId: String,
        val navigationState: NavigationState
    ) : FeedEvent
    data object OnShowMoreVacanciesButtonClicked : FeedEvent
}