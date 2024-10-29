package com.manuelklyukvin.favorite.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.core.domain.vacancies.ToggleFavoriteStatusUseCase
import com.manuelklyukvin.core.presentation.ui.navigation.NavigationState
import com.manuelklyukvin.core.presentation.ui.navigation.Screen
import com.manuelklyukvin.core.presentation.vacancies.models.toPresentation
import com.manuelklyukvin.favorite.domain.vacancies.GetFavoriteVacanciesUseCase
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteEvent
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteState
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase,
    private val toggleFavoriteStatusUseCase: ToggleFavoriteStatusUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState>
        get() = _state.asStateFlow()

    init {
        loadFavoriteVacancies()
    }

    fun onEvent(event: FavoriteEvent) = when (event) {
        is FavoriteEvent.OnVacancyClicked -> onVacancyClicked(
            vacancyId = event.vacancyId,
            navigationState = event.navigationState
        )
        is FavoriteEvent.OnFavoriteButtonClicked -> onFavoriteButtonClicked(event.vacancyId)
    }

    private fun loadFavoriteVacancies() {
        _state.value = _state.value.copy(viewState = FavoriteViewState.LOADING)

        viewModelScope.launch {
            when (val result = getFavoriteVacanciesUseCase()) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        vacancyList = result.data.map { domainVacancy ->
                            domainVacancy.toPresentation()
                        },
                        viewState = FavoriteViewState.CONTENT
                    )
                }
                is Result.Error -> {
                    Log.e("Test", result.error.toString())
                    _state.value = _state.value.copy(viewState = FavoriteViewState.ERROR)
                }
            }
        }
    }

    private fun onVacancyClicked(
        vacancyId: String,
        navigationState: NavigationState
    ) {
        navigationState.navigate(Screen.Vacancy(vacancyId))
    }

    private fun onFavoriteButtonClicked(vacancyId: String) {
        viewModelScope.launch {
            toggleFavoriteStatusUseCase(vacancyId)
        }
    }
}