package com.manuelklyukvin.feed.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelklyukvin.core.presentation.components.AppButton
import com.manuelklyukvin.core.presentation.components.AppCard
import com.manuelklyukvin.core.presentation.components.AppIcon
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.core.presentation.theme.LocalNavigationState
import com.manuelklyukvin.core.presentation.utils.noIndicationClickable
import com.manuelklyukvin.core.vacancies.models.Vacancy
import com.manuelklyukvin.feed.presentation.R
import com.manuelklyukvin.feed.presentation.screen.models.FeedEvent
import com.manuelklyukvin.core.presentation.R as CoreR

@Composable
fun VacancyCard(vacancy: Vacancy, onEvent: (FeedEvent) -> Unit) {
    val navigationState = LocalNavigationState.current

    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .noIndicationClickable {
                onEvent(FeedEvent.OnVacancyClicked(
                    vacancyId = vacancy.id,
                    navigationState = navigationState
                ))
            },
        paddingValues = PaddingValues(AppTheme.shapes.paddingMedium)
    ) {
        Row {
            VacancyDetails(vacancy)
            Spacer(modifier = Modifier.width(AppTheme.shapes.paddingMedium))
            VacancyLikeButton(vacancy)
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
            salary.short?.let {
                Text(
                    text = it,
                    style = AppTheme.typography.title2,
                    color = AppTheme.colorScheme.white
                )
            }
            EmployerDetails(this)
            Experience(this)
            Text(
                text = stringResource(R.string.feed_vacancy_published_date, publishedDate),
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
                    painter = painterResource(CoreR.drawable.verified),
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
private fun VacancyLikeButton(vacancy: Vacancy) {
    var isFavorite by remember {
        mutableStateOf(vacancy.isFavorite)
    }

    val painter: Painter
    val tint: Color

    if (isFavorite) {
        painter = painterResource(CoreR.drawable.favorite_enabled)
        tint = AppTheme.colorScheme.blue
    } else {
        painter = painterResource(com.manuelklyukvin.core.presentation.R.drawable.favorite)
        tint = AppTheme.colorScheme.gray4
    }

    IconButton(
        modifier = Modifier.size(AppTheme.shapes.sizeSmall),
        onClick = {
            isFavorite = !isFavorite
        }
    ) {
        AppIcon(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            tint = tint
        )
    }
}