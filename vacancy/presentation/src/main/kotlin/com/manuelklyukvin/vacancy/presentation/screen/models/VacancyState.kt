package com.manuelklyukvin.vacancy.presentation.screen.models

import androidx.compose.foundation.text.input.TextFieldState
import com.manuelklyukvin.core.presentation.vacancies.models.Vacancy

data class VacancyState(
    val viewState: VacancyViewState = VacancyViewState.INITIAL,
    val vacancy: Vacancy? = null,
    val isVacancyFavorite: Boolean = false,
    val isReplyScreenShown: Boolean = false,
    val isAddLetterScreenShown: Boolean = false,
    val letterState: TextFieldState = TextFieldState()
)