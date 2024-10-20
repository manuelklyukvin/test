package com.manuelklyukvin.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed interface Screen {

    @Serializable
    data object SignIn : Screen
    @Serializable
    data class Code(val email: String) : Screen

    @Serializable
    data object Feed : Screen
    @Serializable
    data object Favorite : Screen
    @Serializable
    data object Replies : Screen
    @Serializable
    data object Messages : Screen
    @Serializable
    data object Profile : Screen
}