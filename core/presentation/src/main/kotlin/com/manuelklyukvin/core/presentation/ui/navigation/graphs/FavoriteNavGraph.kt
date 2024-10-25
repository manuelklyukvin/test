package com.manuelklyukvin.core.presentation.ui.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.manuelklyukvin.core.presentation.ui.navigation.Screen

fun NavGraphBuilder.favoriteNavGraph(
    favoriteScreen: @Composable () -> Unit,
    vacancyScreen: @Composable (String) -> Unit
) {
    navigation<Screen.FavoriteBlock>(startDestination = Screen.Favorite) {
        composable<Screen.Favorite> {
            favoriteScreen()
        }
        composable<Screen.Vacancy> {
            val vacancyId = it.toRoute<Screen.Vacancy>().vacancyId
            vacancyScreen(vacancyId)
        }
    }
}