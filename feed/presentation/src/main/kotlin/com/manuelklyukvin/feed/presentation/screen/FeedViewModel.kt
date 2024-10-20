package com.manuelklyukvin.feed.presentation.screen

import android.util.Log
import androidx.compose.foundation.text.input.TextFieldState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuelklyukvin.core.domain.result.Result
import com.manuelklyukvin.feed.domain.offer.GetOffersUseCase
import com.manuelklyukvin.feed.presentation.screen.models.Offer
import com.manuelklyukvin.feed.presentation.screen.models.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedViewModel @Inject constructor(
    private val getOffersUseCase: GetOffersUseCase
) : ViewModel() {

    private val _searchState = MutableStateFlow(TextFieldState())
    val searchState: StateFlow<TextFieldState>
        get() = _searchState.asStateFlow()

    private val _offerList = MutableStateFlow<List<Offer>>(emptyList())
    val offerList: StateFlow<List<Offer>>
        get() = _offerList.asStateFlow()

    init {
        loadOffers()
    }

    private fun loadOffers() {
        viewModelScope.launch {
            when (val result = getOffersUseCase()) {
                is Result.Success -> {
                    _offerList.value = result.data.map { domainOffer ->
                        domainOffer.toPresentation()
                    }
                }
                is Result.Error -> Log.e("Test", result.error.toString())
            }
        }
    }
}