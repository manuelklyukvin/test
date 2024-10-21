package com.manuelklyukvin.feed.presentation.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelklyukvin.core.domain.result.Result
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
    private val getVacanciesUseCase: GetVacanciesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FeedState())
    val state: StateFlow<FeedState>
        get() = _state.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(event: FeedEvent) = when (event) {
        is FeedEvent.OnVacancyClicked -> onVacancyClicked(
            vacancyId = event.vacancyId,
            navigationState = event.navigationState
        )
        FeedEvent.OnShowMoreVacanciesButtonClicked -> onShowMoreVacanciesButtonClicked()
    }

    private fun loadData() {
        _state.value = state.value.copy(viewState = FeedViewState.LOADING)

        viewModelScope.launch {
            val offersResult = getOffersUseCase()
            val vacanciesResult = getVacanciesUseCase()

            when {
                offersResult is Result.Error -> {
                    Log.e("Test", offersResult.error.toString())
                    _state.value = state.value.copy(viewState = FeedViewState.ERROR)
                    return@launch
                }
                vacanciesResult is Result.Error -> {
                    Log.e("Test", vacanciesResult.error.toString())
                    _state.value = state.value.copy(viewState = FeedViewState.ERROR)
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
                        viewState = FeedViewState.PREVIEW
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
        _state.value = state.value.copy(viewState = FeedViewState.FULL)
    }
}