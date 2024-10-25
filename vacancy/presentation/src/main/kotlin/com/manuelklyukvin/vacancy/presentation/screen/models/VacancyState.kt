package com.manuelklyukvin.vacancy.presentation.screen.models

import com.manuelklyukvin.core.presentation.vacancies.models.Vacancy

data class VacancyState(
    val viewState: VacancyViewState = VacancyViewState.INITIAL,
    val vacancy: Vacancy? = null,
    val isVacancyFavorite: Boolean = false
)