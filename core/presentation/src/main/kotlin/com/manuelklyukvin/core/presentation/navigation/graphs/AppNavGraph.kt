package com.manuelklyukvin.core.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
        composable<Screen.SignIn> {
            signInScreen()
        }
        composable<Screen.Code> {
            val email = it.toRoute<Screen.Code>().email
            codeScreen(email)
        }
        composable<Screen.Feed> {
            feedScreen()
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