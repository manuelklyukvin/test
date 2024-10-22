package com.manuelklyukvin.search.presentation.screen.models

import androidx.compose.foundation.text.input.TextFieldState
import com.manuelklyukvin.core.vacancies.models.Vacancy

data class SearchState(
    val viewState: SearchViewState = SearchViewState.INITIAL,
    val searchState: TextFieldState = TextFieldState(),
    val offerList: List<Offer> = emptyList(),
    val vacancyList: List<Vacancy> = emptyList()
)