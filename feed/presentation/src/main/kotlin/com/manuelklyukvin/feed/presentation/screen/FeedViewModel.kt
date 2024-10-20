package com.manuelklyukvin.feed.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.core.domain.vacancies.FormatPublishedDateUseCase
import com.manuelklyukvin.core.domain.vacancies.GetVacanciesUseCase
import com.manuelklyukvin.core.presentation.navigation.NavigationState
import com.manuelklyukvin.core.presentation.navigation.Screen
import com.manuelklyukvin.core.vacancies.models.toPresentation
import com.manuelklyukvin.feed.domain.offers.GetOffersUseCase
import com.manuelklyukvin.feed.presentation.screen.models.FeedEvent
import com.manuelklyukvin.feed.presentation.screen.models.FeedState
import com.manuelklyukvin.feed.presentation.screen.models.FeedViewState
import com.manuelklyukvin.feed.presentation.screen.models.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val formatPublishedDateUseCase: FormatPublishedDateUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FeedState())
    val state: StateFlow<FeedState>
        get() = _state.asStateFlow()

    init {
        _state.value = state.value.copy(viewState = FeedViewState.LOADING)
        loadOffers()
        loadVacancies()
        _state.value = state.value.copy(viewState = FeedViewState.PREVIEW)
    }

    fun onEvent(event: FeedEvent) = when (event) {
        is FeedEvent.OnVacancyClicked -> onVacancyClicked(
            vacancyId = event.vacancyId,
            navigationState = event.navigationState
        )
        FeedEvent.OnShowMoreVacanciesButtonClicked -> onShowMoreVacancies()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            when (val result = getOffersUseCase()) {
                is Result.Success -> {
                    _state.value = state.value.copy(
                        offerList = result.data.map { domainOffer ->
                            domainOffer.toPresentation()
                        }
                    )
                }
                is Result.Error -> Log.e("Test", result.error.toString())
            }
        }
    }

    private fun loadVacancies() {
        viewModelScope.launch {
            when (val result = getVacanciesUseCase()) {
                is Result.Success -> {
                    _state.value = state.value.copy(
                        vacancyList = result.data.map { domainVacancy ->
                            domainVacancy.toPresentation().copy(
                                publishedDate = formatPublishedDateUseCase(domainVacancy.publishedDate)
                            )
                        }
                    )
                }
                is Result.Error -> Log.e("Test", result.error.toString())
            }
        }
    }

    private fun onVacancyClicked(
        vacancyId: String,
        navigationState: NavigationState
    ) {
        navigationState.navigate(Screen.Vacancy(vacancyId))
    }

    private fun onShowMoreVacancies() {
        _state.value = state.value.copy(viewState = FeedViewState.FULL)
    }
}