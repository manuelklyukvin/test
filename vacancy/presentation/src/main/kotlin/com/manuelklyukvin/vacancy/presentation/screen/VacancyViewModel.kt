package com.manuelklyukvin.vacancy.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.core.domain.vacancies.ToggleFavoriteStatusUseCase
import com.manuelklyukvin.core.presentation.ui.navigation.NavigationState
import com.manuelklyukvin.core.presentation.vacancies.models.toPresentation
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
    private val getVacancyByIdUseCase: GetVacancyByIdUseCase,
    private val toggleFavoriteStatusUseCase: ToggleFavoriteStatusUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(VacancyState())
    val state: StateFlow<VacancyState>
        get() = _state.asStateFlow()

    fun onEvent(event: VacancyEvent) = when (event) {
        is VacancyEvent.OnScreenInit -> loadVacancy(event.vacancyId)
        is VacancyEvent.OnBackButtonClicked -> onBackButtonClicked(event.navigationState)
        VacancyEvent.OnFavoriteButtonClicked -> onFavoriteButtonClicked()
        VacancyEvent.OnReplyButtonClicked -> onReplyButtonClicked()
        VacancyEvent.OnReplyScreenClosed -> onReplyScreenClosed()
        VacancyEvent.OnAddLetterButtonClicked -> onAddLetterButtonClicked()
        VacancyEvent.OnAddLetterScreenClosed -> onAddLetterScreenClosed()
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
        viewModelScope.launch {
            toggleFavoriteStatusUseCase(state.value.vacancy!!.id)
            _state.value = state.value.copy(isVacancyFavorite = !state.value.isVacancyFavorite)
        }
    }

    private fun onReplyButtonClicked() {
        _state.value = state.value.copy(isReplyScreenShown = true)
    }

    private fun onReplyScreenClosed() {
        _state.value = state.value.copy(isReplyScreenShown = false)
    }

    private fun onAddLetterButtonClicked() {
        _state.value = state.value.copy(
            isAddLetterScreenShown = true,
            isReplyScreenShown = false
        )
    }

    private fun onAddLetterScreenClosed() {
        _state.value = state.value.copy(isAddLetterScreenShown = false)
    }
}