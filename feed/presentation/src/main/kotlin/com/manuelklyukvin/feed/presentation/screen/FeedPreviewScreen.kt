package com.manuelklyukvin.feed.presentation.screen

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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelklyukvin.core.presentation.components.AppButton
import com.manuelklyukvin.core.presentation.components.AppCard
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.feed.presentation.R
import com.manuelklyukvin.feed.presentation.screen.models.FeedEvent
import com.manuelklyukvin.feed.presentation.screen.models.FeedState
import com.manuelklyukvin.feed.presentation.screen.models.Offer

private const val VACANCY_TO_SHOW_COUNT = 3

@Composable
fun FeedPreviewScreen(state: FeedState, onEvent: (FeedEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingMedium)
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
private fun OffersBlock(state: FeedState) {
    val offerList = state.offerList

    if (offerList.isNotEmpty()) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingMedium)
        ) {
            items(offerList) {
                offer -> OfferCard(offer)
            }
        }
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingExtraLarge))
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
    if (offer.buttonText != null) {
        Text(
            text = offer.buttonText,
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.green
        )
    }
}

@Composable
private fun VacanciesBlock(state: FeedState, onEvent: (FeedEvent) -> Unit) {
    val vacanciesToShow = state.vacancyList.take(VACANCY_TO_SHOW_COUNT)

    if (vacanciesToShow.isNotEmpty()) {
        Text(
            text = stringResource(R.string.feed_vacancies),
            style = AppTheme.typography.title2,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppTheme.shapes.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingSmall)
        ) {
            vacanciesToShow.forEach { vacancy ->
                VacancyCard(vacancy, onEvent)
            }
        }
        ShowMoreVacanciesButton(state, onEvent)
    }
}

@Composable
private fun ShowMoreVacanciesButton(state: FeedState, onEvent: (FeedEvent) -> Unit) {
    val vacancyCount = state.vacancyList.size

    if (vacancyCount > VACANCY_TO_SHOW_COUNT) {
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.shapes.sizeExtraLarge),
            onClick = {
                onEvent(FeedEvent.OnShowMoreVacanciesButtonClicked)
            },
            text = stringResource(R.string.feed_show_more_vacancies_button, vacancyCount - VACANCY_TO_SHOW_COUNT)
        )
    }
}

@Preview
@Composable
private fun FeedPreviewScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.black
        ) {
            FeedScreen(
                state = FeedState(),
                onEvent = {  }
            )
        }
    }
}