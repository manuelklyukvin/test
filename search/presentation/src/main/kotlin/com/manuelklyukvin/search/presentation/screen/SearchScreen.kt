package com.manuelklyukvin.search.presentation.screen

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
import com.manuelklyukvin.search.presentation.R
import com.manuelklyukvin.search.presentation.screen.models.SearchEvent
import com.manuelklyukvin.search.presentation.screen.models.SearchState
import com.manuelklyukvin.search.presentation.screen.models.SearchViewState
import com.manuelklyukvin.core.presentation.R as CoreR

@Composable
fun SearchScreen(state: SearchState, onEvent: (SearchEvent) -> Unit) {
    val viewState = state.viewState

    AppScaffold(
        topBar = {
            SearchBar(state)
        },
        floatingActionButton = {
            if (viewState == SearchViewState.FULL) {
                MapButton()
            }
        }
    ) {
        when (viewState) {
            SearchViewState.INITIAL -> Unit
            SearchViewState.LOADING -> LoadingScreen()
            SearchViewState.PREVIEW -> SearchPreviewScreen(state, onEvent)
            SearchViewState.FULL -> SearchFullScreen(state, onEvent)
            SearchViewState.ERROR -> Unit
        }
    }
}

@Composable
private fun SearchBar(state: SearchState) {
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
                hint = stringResource(R.string.search_hint)
            )
            Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
            FilterButton()
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
            text = stringResource(R.string.search_map_button),
            style = AppTheme.typography.title3,
            color = AppTheme.colorScheme.white
        )
    }
}