package com.manuelklyukvin.favorite.presentation.screen.models

import com.manuelklyukvin.core.vacancies.models.Vacancy

data class FavoriteListState(
    val viewState: FavoriteListViewState = FavoriteListViewState.INITIAL,
    val vacancyList: List<Vacancy> = emptyList()
)