package com.manuelklyukvin.feed.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.manuelklyukvin.core.presentation.components.AppIcon
import com.manuelklyukvin.core.presentation.components.AppScaffold
import com.manuelklyukvin.core.presentation.components.AppTextField
import com.manuelklyukvin.core.presentation.components.LoadingScreen
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.feed.presentation.R
import com.manuelklyukvin.feed.presentation.screen.models.FeedEvent
import com.manuelklyukvin.feed.presentation.screen.models.FeedState
import com.manuelklyukvin.feed.presentation.screen.models.FeedViewState
import com.manuelklyukvin.core.presentation.R as CoreR

@Composable
fun FeedScreen(state: FeedState, onEvent: (FeedEvent) -> Unit) {
    val viewState = state.viewState

    AppScaffold(
        topBar = {
            SearchBar(state)
        },
        floatingActionButton = {
            if (viewState == FeedViewState.FULL) {
                MapButton()
            }
        }
    ) {
        when (viewState) {
            FeedViewState.INITIAL -> Unit
            FeedViewState.LOADING -> LoadingScreen()
            FeedViewState.PREVIEW -> FeedPreviewScreen(state, onEvent)
            FeedViewState.FULL -> FeedFullScreen(state, onEvent)
            FeedViewState.ERROR -> Unit
        }
    }
}

@Composable
private fun SearchBar(state: FeedState) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.shapes.paddingMedium)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            AppTextField(
                modifier = Modifier
                    .height(AppTheme.shapes.sizeLarge)
                    .weight(1f),
                state = state.searchState,
                icon = painterResource(CoreR.drawable.search),
                hint = stringResource(R.string.feed_search_hint)
            )
            Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
            FilterButton()
        }
        if (state.viewState == FeedViewState.FULL) {
            Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
            Filter(state)
        }
    }
}

@Composable
private fun FilterButton() {
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

@Composable
private fun Filter(state: FeedState) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.feed_filter_counter, state.vacancyList.size),
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.feed_filter_type),
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.blue
        )
        Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
        AppIcon(
            modifier = Modifier.size(AppTheme.shapes.sizeExtraSmall),
            painter = painterResource(R.drawable.arrows),
            tint = AppTheme.colorScheme.blue
        )
    }
}

@Composable
private fun MapButton() {
    Row(
        modifier = Modifier
            .height(AppTheme.shapes.sizeExtraLarge)
            .background(
                color = AppTheme.colorScheme.gray2,
                shape = AppTheme.shapes.radiusLarge
            )
            .padding(horizontal = AppTheme.shapes.paddingSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppIcon(
            modifier = Modifier.size(AppTheme.shapes.sizeSmall),
            painter = painterResource(R.drawable.map_button)
        )
        Spacer(modifier = Modifier.width(AppTheme.shapes.paddingSmall))
        Text(
            text = stringResource(R.string.feed_map_button),
            style = AppTheme.typography.title3,
            color = AppTheme.colorScheme.white
        )
    }
}