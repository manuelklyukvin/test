package com.manuelklyukvin.favorite.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.manuelklyukvin.core.presentation.components.AppScaffold
import com.manuelklyukvin.core.presentation.components.LoadingScreen
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.core.presentation.theme.LocalNavigationState
import com.manuelklyukvin.core.vacancies.VacanciesPreviewBlock
import com.manuelklyukvin.favorite.presentation.R
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteListEvent
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteListState
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteListViewState

@Composable
fun FavoriteScreen(state: FavoriteListState, onEvent: (FavoriteListEvent) -> Unit) {
    AppScaffold {
        when (state.viewState) {
            FavoriteListViewState.INITIAL -> Unit
            FavoriteListViewState.LOADING -> LoadingScreen()
            FavoriteListViewState.CONTENT -> Content(state, onEvent)
            FavoriteListViewState.ERROR -> Unit
        }
    }
}

@Composable
private fun Content(state: FavoriteListState, onEvent: (FavoriteListEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingMedium)
    ) {
        item {
            Title(state)
        }
        item {
            VacanciesBlock(state, onEvent)
        }
    }
}

@Composable
private fun Title(state: FavoriteListState) {
    Column(modifier = Modifier.padding(top = AppTheme.shapes.paddingExtraLarge)) {
        Text(
            text = stringResource(R.string.favorite_title),
            style = AppTheme.typography.title2,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingLarge))
        Text(
            text = stringResource(R.string.favorite_count, state.vacancyList.size),
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.gray3
        )
    }
}

@Composable
private fun VacanciesBlock(state: FavoriteListState, onEvent: (FavoriteListEvent) -> Unit) {
    val navigationState = LocalNavigationState.current

    VacanciesPreviewBlock(
        vacancyList = state.vacancyList,
        onVacancyClick = { vacancy ->
            onEvent(
                FavoriteListEvent.OnVacancyClicked(
                    vacancyId = vacancy.id,
                    navigationState = navigationState
                )
            )
        }
    )
}