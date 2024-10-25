package com.manuelklyukvin.core.presentation.ui.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object AuthBlock : Screen
    @Serializable
    data object SignIn : Screen
    @Serializable
    data class Code(val email: String) : Screen

    @Serializable
    data class Vacancy(val vacancyId: String) : Screen

    @Serializable
    data object SearchBlock : Screen
    @Serializable
    data object Search : Screen

    @Serializable
    data object FavoriteBlock : Screen
    @Serializable
    data object Favorite : Screen

    @Serializable
    data object Replies : Screen
    @Serializable
    data object Messages : Screen
    @Serializable
    data object Profile : Screen
}