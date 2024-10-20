package com.manuelklyukvin.feed.presentation.screen.models

import androidx.compose.foundation.text.input.TextFieldState
import com.manuelklyukvin.core.vacancies.models.Vacancy

data class FeedState(
    val viewState: FeedViewState = FeedViewState.INITIAL,
    val searchState: TextFieldState = TextFieldState(),
    val offerList: List<Offer> = emptyList(),
    val vacancyList: List<Vacancy> = emptyList()
)