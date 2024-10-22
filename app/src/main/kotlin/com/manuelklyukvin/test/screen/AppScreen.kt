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
import com.manuelklyukvin.favorite.presentation.screen.FavoriteScreen
import com.manuelklyukvin.favorite.presentation.screen.FavoriteViewModel
import com.manuelklyukvin.search.presentation.screen.SearchScreen
import com.manuelklyukvin.search.presentation.screen.SearchViewModel
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
                vacancyScreen = { vacancyId ->
                    val viewModel = hiltViewModel<VacancyViewModel>()
                    val state by viewModel.state.collectAsState()
                    val onEvent = viewModel::onEvent

                    LaunchedEffect(vacancyId) {
                        onEvent(VacancyEvent.OnScreenInit(vacancyId))
                    }

                    VacancyScreen(state, onEvent)
                },
                searchScreen = {
                    val viewModel = hiltViewModel<SearchViewModel>()
                    val state by viewModel.state.collectAsState()
                    val onEvent = viewModel::onEvent

                    SearchScreen(state, onEvent)
                },
                favoriteScreen = {
                    val viewModel = hiltViewModel<FavoriteViewModel>()
                    val state by viewModel.state.collectAsState()
                    val onEvent = viewModel::onEvent

                    FavoriteScreen(state, onEvent)
                }
            )
        }
    }
}