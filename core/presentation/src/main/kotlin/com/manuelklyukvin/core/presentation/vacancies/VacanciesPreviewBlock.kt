package com.manuelklyukvin.core.presentation.vacancies

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.manuelklyukvin.core.presentation.R
import com.manuelklyukvin.core.presentation.ui.components.AppButton
import com.manuelklyukvin.core.presentation.ui.components.AppCard
import com.manuelklyukvin.core.presentation.ui.components.AppIcon
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme
import com.manuelklyukvin.core.presentation.ui.utils.noIndicationClickable
import com.manuelklyukvin.core.presentation.vacancies.models.Vacancy

@Composable
fun VacanciesPreviewBlock(
    vacancyList: List<Vacancy>,
    onVacancyClick: (Vacancy) -> Unit,
    onFavoriteButtonClicked: (Vacancy) -> Unit
) {
    if (vacancyList.isNotEmpty()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = AppTheme.shapes.paddingMedium),
            verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingMedium)
        ) {
            vacancyList.forEach { vacancy ->
                VacancyCard(
                    vacancy = vacancy,
                    onVacancyClicked = {
                        onVacancyClick(vacancy)
                    },
                    onFavoriteButtonClicked = {
                        onFavoriteButtonClicked(vacancy)
                    }
                )
            }
        }
    }
}

@Composable
private fun VacancyCard(
    vacancy: Vacancy,
    onVacancyClicked: () -> Unit,
    onFavoriteButtonClicked: () -> Unit
) {
    AppCard(
        modifier = Modifier
            .fillMaxWidth()
            .noIndicationClickable {
                onVacancyClicked()
            },
        paddingValues = PaddingValues(AppTheme.shapes.paddingMedium)
    ) {
        Row {
            VacancyDetails(vacancy)
            Spacer(modifier = Modifier.width(AppTheme.shapes.paddingMedium))
            VacancyFavoriteButton(vacancy, onFavoriteButtonClicked)
        }
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
        AppButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(AppTheme.shapes.sizeMedium),
            text = stringResource(R.string.core_vacancy_reply),
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
                    text = stringResource(R.string.core_vacancy_looking_number, lookingNumber),
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
                text = stringResource(R.string.core_vacancy_published_date, publishedDate),
                style = AppTheme.typography.text1,
                color = AppTheme.colorScheme.gray3
            )
        }
    }
}

@Composable
private fun EmployerDetails(vacancy: Vacancy) {
    Column {
        Text(
            text = vacancy.address.town,
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.height(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = vacancy.company,
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

@Composable
private fun Experience(vacancy: Vacancy) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        AppIcon(painter = painterResource(R.drawable.experience))
        Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
        Text(
            text = vacancy.experience.previewText,
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.white
        )
    }
}

@Composable
private fun VacancyFavoriteButton(vacancy: Vacancy, onFavoriteButtonClicked: () -> Unit) {
    var isFavorite by remember {
        mutableStateOf(vacancy.isFavorite)
    }

    val painter: Painter
    val tint: Color

    if (isFavorite) {
        painter = painterResource(R.drawable.favorite_enabled)
        tint = AppTheme.colorScheme.blue
    } else {
        painter = painterResource(R.drawable.favorite)
        tint = AppTheme.colorScheme.gray4
    }

    AppIcon(
        modifier = Modifier
            .size(AppTheme.shapes.sizeSmall)
            .noIndicationClickable {
                onFavoriteButtonClicked()
                isFavorite = !isFavorite
            },
        painter = painter,
        tint = tint
    )
}