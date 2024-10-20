package com.manuelklyukvin.feed.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.manuelklyukvin.core.presentation.components.AppCard
import com.manuelklyukvin.core.presentation.components.AppIcon
import com.manuelklyukvin.core.presentation.components.AppScaffold
import com.manuelklyukvin.core.presentation.components.AppTextField
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.feed.presentation.R
import com.manuelklyukvin.feed.presentation.screen.models.Offer
import com.manuelklyukvin.core.presentation.R as CoreR

@Composable
fun FeedScreen(viewModel: FeedViewModel = hiltViewModel()) {
    AppScaffold(
        topBar = {
            SearchBar(viewModel)
        }
    ) {
        Content(viewModel)
    }
}

@Composable
private fun SearchBar(viewModel: FeedViewModel) {
    val searchState by viewModel.searchState.collectAsState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.shapes.paddingMedium)
    ) {
        AppTextField(
            modifier = Modifier
                .height(AppTheme.shapes.sizeLarge)
                .weight(1f),
            state = searchState,
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
private fun Content(viewModel: FeedViewModel) {
    val offerList by viewModel.offerList.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingMedium)
    ) {
        Offers(offerList)
    }
}

@Composable
private fun Offers(offerList: List<Offer>) {
    LazyRow (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingSmall)
    ) {
        items(offerList) { offer ->
            OfferCard(offer)
        }
    }
}

@Composable
private fun OfferCard(offer: Offer) {
    AppCard(
        modifier = Modifier
            .width(132.dp)
            .height(120.dp),
        paddingValues = PaddingValues(AppTheme.shapes.paddingSmall),
    ) {
        // Откуда брать картинки? В JSON-е их нет
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