package com.manuelklyukvin.core.presentation.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.manuelklyukvin.core.presentation.ui.navigation.Screen

fun NavGraphBuilder.authNavGraph(
    signInScreen: @Composable () -> Unit,
    codeScreen: @Composable (String) -> Unit
) {
    navigation<Screen.AuthBlock>(startDestination = Screen.SignIn) {
        composable<Screen.SignIn> {
            signInScreen()
        }
        composable<Screen.Code> {
            val email = it.toRoute<Screen.Code>().email
            codeScreen(email)
        }
    }
}