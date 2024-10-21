package com.manuelklyukvin.feed.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.feed.presentation.screen.models.FeedEvent
import com.manuelklyukvin.feed.presentation.screen.models.FeedState

@Composable
fun FeedFullScreen(state: FeedState, onEvent: (FeedEvent) -> Unit) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingMedium)
    ) {
        item {
            VacanciesBlock(state, onEvent)
        }
    }
}

@Composable
private fun VacanciesBlock(state: FeedState, onEvent: (FeedEvent) -> Unit) {
    val vacancyList = state.vacancyList

    if (vacancyList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = AppTheme.shapes.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingSmall)
        ) {
            vacancyList.forEach { vacancy ->
                VacancyCard(vacancy, onEvent)
            }
        }
    }
}