package com.manuelklyukvin.favorite.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.core.presentation.navigation.NavigationState
import com.manuelklyukvin.core.presentation.navigation.Screen
import com.manuelklyukvin.core.vacancies.models.toPresentation
import com.manuelklyukvin.favorite.domain.vacancies.GetFavoriteVacanciesUseCase
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteListEvent
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteListState
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteListViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val getFavoriteVacanciesUseCase: GetFavoriteVacanciesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteListState())
    val state: StateFlow<FavoriteListState>
        get() = _state.asStateFlow()

    init {
        loadFavoriteVacancies()
    }

    fun onEvent(event: FavoriteListEvent) = when (event) {
        is FavoriteListEvent.OnVacancyClicked -> onVacancyClicked(
            vacancyId = event.vacancyId,
            navigationState = event.navigationState
        )
    }

    private fun loadFavoriteVacancies() {
        _state.value = _state.value.copy(viewState = FavoriteListViewState.LOADING)

        viewModelScope.launch {
            when (val result = getFavoriteVacanciesUseCase()) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        vacancyList = result.data.map { domainVacancy ->
                            domainVacancy.toPresentation()
                        },
                        viewState = FavoriteListViewState.CONTENT
                    )
                }
                is Result.Error -> {
                    Log.e("Test", result.error.toString())
                    _state.value = _state.value.copy(viewState = FavoriteListViewState.ERROR)
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
}