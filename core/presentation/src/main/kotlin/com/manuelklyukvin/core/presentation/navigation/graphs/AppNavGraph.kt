package com.manuelklyukvin.core.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.manuelklyukvin.core.presentation.components.AppScaffold
import com.manuelklyukvin.core.presentation.navigation.Screen
import com.manuelklyukvin.core.presentation.theme.LocalNavigationState
import com.manuelklyukvin.core.presentation.theme.resources.Animations

@Composable
fun AppNavGraph(
    signInScreen: @Composable () -> Unit,
    codeScreen: @Composable (String) -> Unit,
    feedScreen: @Composable () -> Unit
) {
    NavHost(
        navController = LocalNavigationState.current.navController,
        startDestination = Screen.Feed,
        enterTransition = Animations.enterTransition,
        exitTransition = Animations.exitTransition,
        popEnterTransition = Animations.enterTransition,
        popExitTransition = Animations.exitTransition
    ) {
        authNavGraph(
            signInScreen = {
                signInScreen()
            },
            codeScreen = { email ->
                codeScreen(email)
            }
        )
        composable<Screen.Feed> {
            feedScreen()
        }
        composable<Screen.Vacancy> {

        }
        composable<Screen.Favorite> {
            AppScaffold {  }
        }
        composable<Screen.Replies> {
            AppScaffold {  }
        }
        composable<Screen.Messages> {
            AppScaffold {  }
        }
        composable<Screen.Profile> {
            AppScaffold {  }
        }
    }
}