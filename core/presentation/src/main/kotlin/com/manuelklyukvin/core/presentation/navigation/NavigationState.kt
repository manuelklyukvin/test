package com.manuelklyukvin.core.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(val navController: NavHostController) {

    fun navigate(route: Any) {
        navController.navigate(route) {
            popUpTo(route) {
                inclusive = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun rememberNavigationState(): NavigationState {
    val navController = rememberNavController()
    return remember {
        NavigationState(navController)
    }
}