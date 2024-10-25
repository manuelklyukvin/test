package com.manuelklyukvin.vacancy.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.manuelklyukvin.core.presentation.ui.components.AppButton
import com.manuelklyukvin.core.presentation.ui.components.AppCard
import com.manuelklyukvin.core.presentation.ui.components.AppIcon
import com.manuelklyukvin.core.presentation.ui.components.AppScaffold
import com.manuelklyukvin.core.presentation.ui.components.LoadingScreen
import com.manuelklyukvin.core.presentation.ui.theme.AppTheme
import com.manuelklyukvin.core.presentation.ui.theme.LocalNavigationState
import com.manuelklyukvin.core.presentation.vacancies.models.Vacancy
import com.manuelklyukvin.vacancy.presentation.R
import com.manuelklyukvin.vacancy.presentation.screen.models.VacancyEvent
import com.manuelklyukvin.vacancy.presentation.screen.models.VacancyState
import com.manuelklyukvin.vacancy.presentation.screen.models.VacancyViewState
import com.manuelklyukvin.vacancy.presentation.utils.VacancyTopBarButton
import com.manuelklyukvin.core.presentation.R as CoreR

@Composable
fun VacancyScreen(state: VacancyState, onEvent: (VacancyEvent) -> Unit, ) {
    val viewState = state.viewState

    AppScaffold(
        topBar = {
            if (viewState == VacancyViewState.CONTENT) {
                TopBar(state, onEvent)
            }
        }
    ) {
        when (viewState) {
            VacancyViewState.INITIAL -> Unit
            VacancyViewState.LOADING -> LoadingScreen()
            VacancyViewState.CONTENT -> Content(state.vacancy!!)
            VacancyViewState.ERROR -> Unit
        }
    }
}

@Composable
private fun TopBar(state: VacancyState, onEvent: (VacancyEvent) -> Unit) {
    val navigationState = LocalNavigationState.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(AppTheme.shapes.paddingMedium),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        VacancyTopBarButton(
            icon = painterResource(R.drawable.arrow_back),
            onClick = {
                onEvent(VacancyEvent.OnBackButtonClicked(navigationState))
            }
        )
        ActionPanel(state, onEvent)
    }
}

@Composable
private fun ActionPanel(state: VacancyState, onEvent: (VacancyEvent) -> Unit) {
    val favoritePainter: Painter
    val favoriteTint: Color

    if (state.isVacancyFavorite) {
        favoritePainter = painterResource(CoreR.drawable.favorite_enabled)
        favoriteTint = AppTheme.colorScheme.blue
    } else {
        favoritePainter = painterResource(CoreR.drawable.favorite)
        favoriteTint = AppTheme.colorScheme.white
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingMedium)
    ) {
        VacancyTopBarButton(icon = painterResource(R.drawable.eye))
        VacancyTopBarButton(icon = painterResource(R.drawable.share))
        VacancyTopBarButton(
            icon = favoritePainter,
            tint = favoriteTint,
            onClick = {
                onEvent(VacancyEvent.OnFavoriteButtonClicked)
            }
        )
    }
}

@Composable
private fun Content(vacancy: Vacancy) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = AppTheme.shapes.paddingMedium),
        verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingLarge)
    ) {
        item {
            Title(vacancy)
        }
        if (vacancy.appliedNumber > 0 || vacancy.lookingNumber > 0) {
            item {
                UserActivityBlock(vacancy)
            }
        }
        item {
            EmployerBlock(vacancy)
        }
        vacancy.description?.let {
            item {
                Description(it)
            }
        }
        if (vacancy.responsibilities.isNotEmpty()) {
            item {
                ResponsibilitiesBlock(vacancy.responsibilities)
            }
        }
        if (vacancy.questions.isNotEmpty()) {
            item {
                QuestionsBlock(vacancy)
            }
        }
        item {
            ReplyButton()
        }
    }
}

@Composable
private fun Title(vacancy: Vacancy) {
    val salaryTextStyle = if (vacancy.salary.short == null) {
        AppTheme.typography.text1
    } else {
        AppTheme.typography.title2
    }

    Text(
        text = vacancy.title,
        style = AppTheme.typography.title1,
        color = AppTheme.colorScheme.white
    )
    Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
    Text(
        text = vacancy.salary.full,
        style = salaryTextStyle,
        color = AppTheme.colorScheme.white
    )
    Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
    Text(
        text = stringResource(R.string.vacancy_experience, vacancy.experience.text),
        style = AppTheme.typography.text1,
        color = AppTheme.colorScheme.white
    )
    Spacer(modifier = Modifier.height(6.dp))
    Text(
        text = vacancy.schedules
            .joinToString(", ")
            .replaceFirstChar {
                it.uppercase()
            },
        style = AppTheme.typography.text1,
        color = AppTheme.colorScheme.white
    )
}

@Composable
private fun UserActivityBlock(vacancy: Vacancy) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingExtraSmall)
    ) {
        if (vacancy.appliedNumber > 0) {
            UserActivityCard(
                text = stringResource(R.string.vacancy_applied_number, vacancy.appliedNumber),
                icon = painterResource(CoreR.drawable.profile)
            )
        }
        if (vacancy.lookingNumber > 0) {
            UserActivityCard(
                text = stringResource(R.string.vacancy_looking_number, vacancy.lookingNumber),
                icon = painterResource(R.drawable.eye)
            )
        }
    }
}

@Composable
private fun RowScope.UserActivityCard(text: String, icon: Painter) {
    Row(
        modifier = Modifier
            .weight(1f)
            .background(
                color = AppTheme.colorScheme.darkGreen,
                shape = AppTheme.shapes.radiusSmall
            )
            .padding(AppTheme.shapes.paddingExtraSmall)
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = text,
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.white
        )
        Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
        Box(
            modifier = Modifier
                .size(AppTheme.shapes.sizeExtraSmall)
                .background(
                    color = AppTheme.colorScheme.green,
                    shape = AppTheme.shapes.radiusLarge
                ),
            contentAlignment = Alignment.Center
        ) {
            AppIcon(
                modifier = Modifier.size(12.dp),
                painter = icon
            )
        }
    }
}

@Composable
private fun EmployerBlock(vacancy: Vacancy) {
    AppCard(
        modifier = Modifier.fillMaxSize(),
        paddingValues = PaddingValues(
            horizontal = AppTheme.shapes.paddingMedium,
            vertical = AppTheme.shapes.paddingSmall
        )
    ) {
        Row {
            Text(
                text = vacancy.company,
                style = AppTheme.typography.title3,
                color = AppTheme.colorScheme.white
            )
            Spacer(modifier = Modifier.width(AppTheme.shapes.paddingExtraSmall))
            AppIcon(
                painter = painterResource(CoreR.drawable.verified),
                tint = AppTheme.colorScheme.gray3
            )
        }
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingExtraSmall))
        Image(
            modifier = Modifier
                .fillMaxSize()
                .height(60.dp),
            painter = painterResource(R.drawable.map),
            contentScale = ContentScale.FillWidth,
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(AppTheme.shapes.paddingExtraSmall))
        Text(
            text = "${vacancy.address.town}, ${vacancy.address.street}, ${vacancy.address.house}",
            style = AppTheme.typography.text1,
            color = AppTheme.colorScheme.white
        )
    }
}

@Composable
fun Description(description: String) {
    Text(
        text = description,
        style = AppTheme.typography.text1,
        color = AppTheme.colorScheme.white
    )
}

@Composable
private fun ResponsibilitiesBlock(responsibilities: String) {
    Text(
        text = stringResource(R.string.vacancy_responsibilities),
        style = AppTheme.typography.title2,
        color = AppTheme.colorScheme.white
    )
    Spacer(modifier = Modifier.height(AppTheme.shapes.paddingExtraSmall))
    Text(
        text = responsibilities,
        style = AppTheme.typography.text1,
        color = AppTheme.colorScheme.white
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuestionsBlock(vacancy: Vacancy) {
    Text(
        modifier = Modifier.padding(top = AppTheme.shapes.paddingExtraSmall),
        text = stringResource(R.string.vacancy_questions_title),
        style = AppTheme.typography.title4,
        color = AppTheme.colorScheme.white
    )
    Spacer(modifier = Modifier.height(AppTheme.shapes.paddingExtraSmall))
    Text(
        text = stringResource(R.string.vacancy_questions_subtitle),
        style = AppTheme.typography.title4,
        color = AppTheme.colorScheme.gray3
    )
    Spacer(modifier = Modifier.height(AppTheme.shapes.paddingMedium))
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingExtraSmall),
        horizontalArrangement = Arrangement.spacedBy(AppTheme.shapes.paddingExtraSmall)
    ) {
        vacancy.questions.forEach { question ->
            Question(question)
        }
    }
}

@Composable
private fun Question(question: String) {
    Box(
        modifier = Modifier
            .background(
                color = AppTheme.colorScheme.gray2,
                shape = AppTheme.shapes.radiusLarge
            )
            .padding(
                vertical = AppTheme.shapes.paddingExtraSmall,
                horizontal = 12.dp
            )
    ) {
        Text(
            text = question,
            style = AppTheme.typography.title4,
            color = AppTheme.colorScheme.white
        )
    }
}

@Composable
private fun ReplyButton() {
    AppButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = AppTheme.shapes.paddingMedium)
            .height(AppTheme.shapes.sizeExtraLarge),
        onClick = {  },
        containerColor = AppTheme.colorScheme.green,
        disabledContainerColor = AppTheme.colorScheme.darkGreen,
        text = stringResource(R.string.vacancy_reply_button),
        textStyle = AppTheme.typography.buttonText1
    )
}