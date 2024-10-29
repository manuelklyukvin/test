package com.manuelklyukvin.search.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelklyukvin.core.presentation.ui.components.AppButton
import com.manuelklyukvin.core.presentation.ui.components.AppCard
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme
import com.manuelklyukvin.core.presentation.ui.theme.LocalNavigationState
import com.manuelklyukvin.core.presentation.vacancies.VacanciesPreviewBlock
import com.manuelklyukvin.search.presentation.R
import com.manuelklyukvin.search.presentation.screen.models.Offer
import com.manuelklyukvin.search.presentation.screen.models.SearchEvent
import com.manuelklyukvin.search.presentation.screen.models.SearchState

private const val VACANCIES_TO_SHOW_COUNT = 3

@Composable
fun SearchPreviewScreen(state: SearchState, onEvent: (SearchEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingExtraLarge)
    ) {
        item {
            OffersBlock(state)
        }
        item {
            VacanciesBlock(state, onEvent)
        }
    }
}

@Composable
private fun OffersBlock(state: SearchState) {
    val offerList = state.offerList

    if (offerList.isNotEmpty()) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingMedium)
        ) {
            item {
                Spacer(Modifier)
            }
            items(offerList) { offer ->
                OfferCard(offer)
            }
            item {
                Spacer(Modifier)
            }
        }
    }
}

@Composable
private fun OfferCard(offer: Offer) {
    AppCard(
        modifier = Modifier
            .width(132.dp)
            .height(120.dp),
        paddingValues = PaddingValues(
            vertical = AppTheme.shapes.paddingSmall,
            horizontal = AppTheme.shapes.paddingExtraSmall
        ),
    ) {
        OfferCardDetails(offer)
    }
}

@Composable
private fun OfferCardDetails(offer: Offer) {
    // Где брать картинки? В JSON-е их нет
    Box(
        modifier = Modifier
            .size(AppTheme.shapes.sizeMedium)
            .background(
                color = AppTheme.colorScheme.darkGreen,
                shape = AppTheme.shapes.radiusLarge
            )
    )
    Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
    Text(
        text = offer.title,
        style = AppTheme.typography.title4,
        color = AppTheme.colorScheme.white
    )
    offer.buttonText?.let {
        Text(
            text = it,
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.green
        )
    }
}

@Composable
private fun VacanciesBlock(state: SearchState, onEvent: (SearchEvent) -> Unit) {
    val vacanciesToShow = state.vacancyList.take(VACANCIES_TO_SHOW_COUNT)

    val navigationState = LocalNavigationState.current

    Column(modifier = Modifier.padding(horizontal = AppTheme.shapes.paddingMedium)) {
        Text(
            text = stringResource(R.string.search_vacancies_title),
            style = AppTheme.typography.title2,
            color = AppTheme.colorScheme.white
        )
        VacanciesPreviewBlock(
            vacancyList = vacanciesToShow,
            onVacancyClick = { vacancy ->
                onEvent(
                    SearchEvent.OnVacancyClicked(
                        vacancyId = vacancy.id,
                        navigationState = navigationState
                    )
                )
            },
            onFavoriteButtonClicked = { vacancy ->
                onEvent(SearchEvent.OnFavoriteButtonClicked(vacancyId = vacancy.id))
            }
        )
        ShowMoreVacanciesButton(state, onEvent)
    }
}

@Composable
private fun ShowMoreVacanciesButton(state: SearchState, onEvent: (SearchEvent) -> Unit) {
    val vacancyCount = state.vacancyList.size

    if (vacancyCount > VACANCIES_TO_SHOW_COUNT) {
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = AppTheme.shapes.paddingExtraSmall,
                    bottom = AppTheme.shapes.paddingMedium
                )
                .height(AppTheme.shapes.sizeExtraLarge),
            onClick = {
                onEvent(SearchEvent.OnShowMoreVacanciesButtonClicked)
            },
            text = stringResource(R.string.search_show_more_vacancies_button, vacancyCount - VACANCIES_TO_SHOW_COUNT),
            textStyle = AppTheme.typography.buttonText1
        )
    }
}