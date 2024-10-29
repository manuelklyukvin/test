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
import com.manuelklyukvin.core.presentation.ui.components.AppScaffold
import com.manuelklyukvin.core.presentation.ui.components.LoadingScreen
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme
import com.manuelklyukvin.core.presentation.ui.theme.LocalNavigationState
import com.manuelklyukvin.core.presentation.vacancies.VacanciesPreviewBlock
import com.manuelklyukvin.favorite.presentation.R
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteEvent
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteState
import com.manuelklyukvin.favorite.presentation.screen.models.FavoriteViewState

@Composable
fun FavoriteScreen(state: FavoriteState, onEvent: (FavoriteEvent) -> Unit) {
    AppScaffold {
        when (state.viewState) {
            FavoriteViewState.INITIAL -> Unit
            FavoriteViewState.LOADING -> LoadingScreen()
            FavoriteViewState.CONTENT -> Content(state, onEvent)
            FavoriteViewState.ERROR -> Unit
        }
    }
}

@Composable
private fun Content(state: FavoriteState, onEvent: (FavoriteEvent) -> Unit) {
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
private fun Title(state: FavoriteState) {
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
private fun VacanciesBlock(state: FavoriteState, onEvent: (FavoriteEvent) -> Unit) {
    val navigationState = LocalNavigationState.current

    VacanciesPreviewBlock(
        vacancyList = state.vacancyList,
        onVacancyClick = { vacancy ->
            onEvent(
                FavoriteEvent.OnVacancyClicked(
                    vacancyId = vacancy.id,
                    navigationState = navigationState
                )
            )
        },
        onFavoriteButtonClicked = { vacancy ->
            onEvent(FavoriteEvent.OnFavoriteButtonClicked(vacancyId = vacancy.id))
        }
    )
}