package com.manuelklyukvin.feed.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.manuelklyukvin.core.presentation.components.AppButton
import com.manuelklyukvin.core.presentation.components.AppCard
import com.manuelklyukvin.core.presentation.components.AppIcon
import com.manuelklyukvin.core.presentation.components.AppScaffold
import com.manuelklyukvin.core.presentation.components.AppTextField
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.feed.presentation.R
import com.manuelklyukvin.feed.presentation.screen.models.FeedState
import com.manuelklyukvin.feed.presentation.screen.models.FeedViewState
import com.manuelklyukvin.feed.presentation.screen.models.Offer
import com.manuelklyukvin.feed.presentation.screen.models.Vacancy
import com.manuelklyukvin.core.presentation.R as CoreR

@Composable
fun FeedScreen(state: FeedState) {
    AppScaffold(
        topBar = {
            SearchBar(state)
        }
    ) {
        when (state.viewState) {
            FeedViewState.INITIAL -> Unit
            FeedViewState.LOADING -> Loading()
            FeedViewState.CONTENT -> Content(state)
        }
    }
}

@Composable
private fun SearchBar(state: FeedState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.shapes.paddingMedium)
    ) {
        AppTextField(
            modifier = Modifier
                .height(AppTheme.shapes.sizeLarge)
                .weight(1f),
            state = state.searchState,
            icon = painterResource(CoreR.drawable.search),
            hint = stringResource(R.string.feed_search_hint)
        )
        Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
        Box(
            modifier = Modifier
                .size(AppTheme.shapes.sizeLarge)
                .background(
                    color = AppTheme.colorScheme.gray2,
                    shape = AppTheme.shapes.radiusSmall
                ),
            contentAlignment = Alignment.Center
        ) {
            AppIcon(
                modifier = Modifier.size(AppTheme.shapes.sizeSmall),
                painter = painterResource(CoreR.drawable.filter)
            )
        }
    }
}

@Composable
private fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(AppTheme.shapes.sizeMedium),
            color = AppTheme.colorScheme.green
        )
    }
}

@Composable
private fun Content(state: FeedState) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingMedium)
    ) {
        item {
            OffersBlock(state.offerList)
        }
        item {
            VacanciesBlock(state.vacancyList)
        }
    }
}

@Composable
private fun OffersBlock(offerList: List<Offer>) {
    if (offerList.isNotEmpty()) {
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingMedium)
        ) {
            items(offerList) { offer ->
                OfferCard(offer)
            }
        }
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingExtraLarge))
    }
}

@Composable
private fun VacanciesBlock(vacancyList: List<Vacancy>) {
    if (vacancyList.isNotEmpty()) {
        Text(
            text = stringResource(R.string.feed_vacancies),
            style = AppTheme.typography.title2,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingSmall)
        ) {
            for (vacancy in vacancyList) {
                VacancyCard(vacancy)
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
}

@Composable
private fun VacancyCard(vacancy: Vacancy) {
    AppCard(
        modifier = Modifier.fillMaxWidth(),
        paddingValues = PaddingValues(AppTheme.shapes.paddingMedium)
    ) {
        Row {
            VacancyDetails(vacancy)
            Spacer(modifier = Modifier.width(AppTheme.shapes.paddingMedium))
            VacancyLikeButton()
        }
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.shapes.sizeMedium),
            text = stringResource(R.string.feed_vacancy_reply),
            shape = AppTheme.shapes.radiusLarge,
            containerColor = AppTheme.colorScheme.green,
            disabledContainerColor = AppTheme.colorScheme.darkGreen,
            onClick = {  }
        )
    }
}

@Composable
private fun RowScope.VacancyDetails(vacancy: Vacancy) {
    Column(
        modifier = Modifier.weight(1f),
        verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingSmall)
    ) {
        with(vacancy) {
            if (lookingNumber > 0) {
                Text(
                    text = stringResource(R.string.feed_vacancy_looking_number, lookingNumber),
                    style = AppTheme.typography.text1,
                    color = AppTheme.colorScheme.green
                )
            }
            Text(
                text = title,
                style = AppTheme.typography.title3,
                color = AppTheme.colorScheme.white
            )
            if (salary.short != null) {
                Text(
                    text = salary.short,
                    style = AppTheme.typography.title2,
                    color = AppTheme.colorScheme.white
                )
            }
            EmployerDetails(this)
            Experience(this)
            Text(
                text = publishedDate,
                style = AppTheme.typography.text1,
                color = AppTheme.colorScheme.gray3
            )
        }
    }
}

@Composable
private fun EmployerDetails(vacancy: Vacancy) {
    with(vacancy) {
        Column {
            Text(
                text = address.town,
                style = AppTheme.typography.text1,
                color = AppTheme.colorScheme.white
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = company,
                    style = AppTheme.typography.text1,
                    color = AppTheme.colorScheme.white
                )
                Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
                AppIcon(
                    painter = painterResource(R.drawable.verified),
                    tint = AppTheme.colorScheme.gray3
                )
            }
        }
    }
}

@Composable
private fun Experience(vacancy: Vacancy) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppIcon(
            painter = painterResource(R.drawable.experience),
            tint = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
        Text(
            text = vacancy.experience.previewText,
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.white
        )
    }
}

@Composable
private fun VacancyLikeButton() {
    IconButton(
        modifier = Modifier.size(AppTheme.shapes.sizeSmall),
        onClick = {  }
    ) {
        AppIcon(
            painter = painterResource(CoreR.drawable.favorite),
            tint = AppTheme.colorScheme.gray4
        )
    }
}

@Preview
@Composable
private fun FeedScreenPreview() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.black
        ) {
            FeedScreen(FeedState())
        }
    }
}