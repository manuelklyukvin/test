package com.manuelklyukvin.test.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.manuelklyukvin.code.presentation.screen.CodeScreen
import com.manuelklyukvin.code.presentation.screen.CodeViewModel
import com.manuelklyukvin.core.presentation.navigation.graphs.AppNavGraph
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.feed.presentation.screen.FeedScreen
import com.manuelklyukvin.feed.presentation.screen.FeedViewModel
import com.manuelklyukvin.sign_in.presentation.screen.SignInScreen
import com.manuelklyukvin.sign_in.presentation.screen.SignInViewModel
import com.manuelklyukvin.vacancy.presentation.screen.VacancyScreen
import com.manuelklyukvin.vacancy.presentation.screen.VacancyViewModel
import com.manuelklyukvin.vacancy.presentation.screen.models.VacancyEvent

@Composable
fun AppScreen() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.black
        ) {
            AppNavGraph(
                signInScreen = {
                    val viewModel = hiltViewModel<SignInViewModel>()
                    val state by viewModel.state.collectAsState()
                    val onEvent = viewModel::onEvent

                    SignInScreen(state, onEvent)
                },
                codeScreen = { email ->
                    val viewModel = hiltViewModel<CodeViewModel>()
                    val state by viewModel.state.collectAsState()
                    val onEvent = viewModel::onEvent

                    CodeScreen(
                        state = state,
                        onEvent = onEvent,
                        email = email
                    )
                },
                feedScreen = {
                    val viewModel = hiltViewModel<FeedViewModel>()
                    val state by viewModel.state.collectAsState()
                    val onEvent = viewModel::onEvent

                    FeedScreen(state, onEvent)
                },
                vacancyScreen = { vacancyId ->
                    val viewModel = hiltViewModel<VacancyViewModel>()
                    val state by viewModel.state.collectAsState()
                    val onEvent = viewModel::onEvent

                    LaunchedEffect(vacancyId) {
                        onEvent(VacancyEvent.OnScreenInit(vacancyId))
                    }

                    VacancyScreen(state, onEvent,)
                }
            )
        }
    }
}