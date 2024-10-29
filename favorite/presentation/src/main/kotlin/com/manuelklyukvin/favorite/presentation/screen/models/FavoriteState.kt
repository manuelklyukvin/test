package com.manuelklyukvin.favorite.presentation.screen.models

import com.manuelklyukvin.core.presentation.vacancies.models.Vacancy

data class FavoriteState(
    val viewState: FavoriteViewState = FavoriteViewState.INITIAL,
    val vacancyList: List<Vacancy> = emptyList()
)