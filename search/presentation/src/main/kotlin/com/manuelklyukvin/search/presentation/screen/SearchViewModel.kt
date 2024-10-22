package com.manuelklyukvin.search.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelklyukvin.core.domain.result.models.Result
import com.manuelklyukvin.core.presentation.navigation.NavigationState
import com.manuelklyukvin.core.presentation.navigation.Screen
import com.manuelklyukvin.core.vacancies.models.toPresentation
import com.manuelklyukvin.search.domain.offers.GetOffersUseCase
import com.manuelklyukvin.search.domain.vacancies.GetVacanciesUseCase
import com.manuelklyukvin.search.presentation.screen.models.SearchEvent
import com.manuelklyukvin.search.presentation.screen.models.SearchState
import com.manuelklyukvin.search.presentation.screen.models.SearchViewState
import com.manuelklyukvin.search.presentation.screen.models.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState>
        get() = _state.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(event: SearchEvent) = when (event) {
        is SearchEvent.OnVacancyClicked -> onVacancyClicked(
            vacancyId = event.vacancyId,
            navigationState = event.navigationState
        )
        SearchEvent.OnShowMoreVacanciesButtonClicked -> onShowMoreVacanciesButtonClicked()
    }

    private fun loadData() {
        _state.value = state.value.copy(viewState = SearchViewState.LOADING)

        viewModelScope.launch {
            val offersResult = getOffersUseCase()
            val vacanciesResult = getVacanciesUseCase()

            when {
                offersResult is Result.Error -> {
                    Log.e("Test", offersResult.error.toString())
                    _state.value = state.value.copy(viewState = SearchViewState.ERROR)
                    return@launch
                }
                vacanciesResult is Result.Error -> {
                    Log.e("Test", vacanciesResult.error.toString())
                    _state.value = state.value.copy(viewState = SearchViewState.ERROR)
                    return@launch
                }
                else -> {
                    _state.value = state.value.copy(
                        offerList = (offersResult as Result.Success).data.map { domainOffer ->
                            domainOffer.toPresentation()
                        },
                        vacancyList = (vacanciesResult as Result.Success).data.map { domainVacancy ->
                            domainVacancy.toPresentation()
                        },
                        viewState = SearchViewState.PREVIEW
                    )
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

    private fun onShowMoreVacanciesButtonClicked() {
        _state.value = state.value.copy(viewState = SearchViewState.FULL)
    }
}