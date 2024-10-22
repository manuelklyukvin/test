package com.manuelklyukvin.vacancy.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.core.presentation.navigation.NavigationState
import com.manuelklyukvin.core.vacancies.models.toPresentation
import com.manuelklyukvin.vacancy.domain.vacancies.GetVacancyByIdUseCase
import com.manuelklyukvin.vacancy.presentation.screen.models.VacancyEvent
import com.manuelklyukvin.vacancy.presentation.screen.models.VacancyState
import com.manuelklyukvin.vacancy.presentation.screen.models.VacancyViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VacancyViewModel @Inject constructor(
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(VacancyState())
    val state: StateFlow<VacancyState>
        get() = _state.asStateFlow()

    fun onEvent(event: VacancyEvent) = when (event) {
        is VacancyEvent.OnScreenInit -> loadVacancy(event.vacancyId)
        is VacancyEvent.OnBackButtonClicked -> onBackButtonClicked(event.navigationState)
        VacancyEvent.OnFavoriteButtonClicked -> onFavoriteButtonClicked()
    }

    private fun loadVacancy(vacancyId: String) {
        _state.value = _state.value.copy(viewState = VacancyViewState.LOADING)

        viewModelScope.launch {
            when (val result = getVacancyByIdUseCase(vacancyId)) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        vacancy = result.data.toPresentation(),
                        isVacancyFavorite = result.data.isFavorite,
                        viewState = VacancyViewState.CONTENT
                    )
                }
                is Result.Error -> {
                    Log.e("Test", result.error.toString())
                    _state.value = _state.value.copy(viewState = VacancyViewState.ERROR)
                }
            }
        }
    }

    private fun onBackButtonClicked(navigationState: NavigationState) {
        navigationState.navController.navigateUp()
    }

    private fun onFavoriteButtonClicked() {
        _state.value = state.value.copy(isVacancyFavorite = !state.value.isVacancyFavorite)
    }
}