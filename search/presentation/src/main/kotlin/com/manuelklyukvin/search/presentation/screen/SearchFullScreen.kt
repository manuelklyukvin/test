package com.manuelklyukvin.search.presentation.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.manuelklyukvin.core.presentation.ui.components.AppIcon
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme
import com.manuelklyukvin.core.presentation.ui.theme.LocalNavigationState
import com.manuelklyukvin.core.presentation.vacancies.VacanciesPreviewBlock
import com.manuelklyukvin.search.presentation.R
import com.manuelklyukvin.search.presentation.screen.models.SearchEvent
import com.manuelklyukvin.search.presentation.screen.models.SearchState

@Composable
fun SearchFullScreen(state: SearchState, onEvent: (SearchEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingMedium)
    ) {
        item {
            Filter(state)
        }
        item {
            VacanciesBlock(state, onEvent)
        }
    }
}

@Composable
private fun Filter(state: SearchState) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = AppTheme.shapes.paddingExtraSmall),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(R.string.search_filter_count, state.vacancyList.size),
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = stringResource(R.string.search_filter_type),
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
private fun VacanciesBlock(state: SearchState, onEvent: (SearchEvent) -> Unit) {
    val navigationState = LocalNavigationState.current

    VacanciesPreviewBlock(
        vacancyList = state.vacancyList,
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
}