package com.manuelklyukvin.core.presentation.navigation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.toRoute
import com.manuelklyukvin.core.presentation.navigation.Screen

fun NavGraphBuilder.searchNavGraph(
    searchScreen: @Composable () -> Unit,
    vacancyScreen: @Composable (String) -> Unit
) {
    navigation<Screen.SearchBlock>(startDestination = Screen.Search) {
        composable<Screen.Search> {
            searchScreen()
        }
        composable<Screen.Vacancy> {
            val vacancyId = it.toRoute<Screen.Vacancy>().vacancyId
            vacancyScreen(vacancyId)
        }
    }
}